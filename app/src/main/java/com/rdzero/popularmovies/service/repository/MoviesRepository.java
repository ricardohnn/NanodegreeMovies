package com.rdzero.popularmovies.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rdzero.popularmovies.BuildConfig;
import com.rdzero.popularmovies.service.model.MovieReviews;
import com.rdzero.popularmovies.service.model.MovieTrailers;
import com.rdzero.popularmovies.service.model.MoviesDetails;
import com.rdzero.popularmovies.service.model.TMDBApiResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {
    private TMDBService tmdbService;
    private static MoviesRepository moviesRepository;

    private MoviesRepository(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request originalRequest  = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY).build();

                // Request customization: add request headers
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .url(url);

                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Gson gson =
                new GsonBuilder()
                        .registerTypeAdapter(MoviesDetails.class, new MyDeserializer<MoviesDetails>())
                        .registerTypeAdapter(MovieReviews.class, new MyDeserializer<MovieReviews>())
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TMDBService.HTTPS_API_TMDB_URL)
                .client(okHttpClient)
                //.addConverterFactory(GsonConverterFactory.create())
                .build();

        tmdbService = retrofit.create(TMDBService.class);
    }

    class MyDeserializer<T> implements JsonDeserializer<T>
    {
        @Override
        public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("results");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, type);

        }
    }

    public synchronized static MoviesRepository getInstance() {
        //TODO Use Dagger here
        if (moviesRepository == null) {
            moviesRepository = new MoviesRepository();
        }
        return moviesRepository;
    }

    public LiveData<List<MoviesDetails>> getMoviesList(String searchType) {
        final MutableLiveData<List<MoviesDetails>> data = new MutableLiveData<>();

        tmdbService.getMoviesList(searchType).enqueue(new Callback<TMDBApiResponse<MoviesDetails>>() {
            @Override
            public void onResponse(Call<TMDBApiResponse<MoviesDetails>> call, Response<TMDBApiResponse<MoviesDetails>> response) {
                Log.d("NAKAi", response.body().toString());
                data.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TMDBApiResponse<MoviesDetails>> call, Throwable t) {
                // TODO Improve error handling
                data.setValue(null);
                Log.d("NAKAe", "error" + t.toString());
            }
        });

        return data;
    }

    public LiveData<List<MovieReviews>> getMovieReviews(String movieId) {
        final MutableLiveData<List<MovieReviews>> data = new MutableLiveData<>();

        tmdbService.getMovieReviews(movieId).enqueue(new Callback<TMDBApiResponse>() {
            @Override
            public void onResponse(Call<TMDBApiResponse> call, Response<TMDBApiResponse> response) {
                data.setValue(response.body().getResults());
                Log.d("NAKAi", data.getValue().toString());
            }

            @Override
            public void onFailure(Call<TMDBApiResponse> call, Throwable t) {
                // TODO Improve error handling
                data.setValue(null);
                Log.d("NAKAe", "error" + t.toString());
            }
        });

        return data;
    }

    public LiveData<List<MovieTrailers>> getMovieTrailers(String movieId) {
        final MutableLiveData<List<MovieTrailers>> data = new MutableLiveData<>();

        tmdbService.getMovieTrailers(movieId).enqueue(new Callback<TMDBApiResponse>() {
            @Override
            public void onResponse(Call<TMDBApiResponse> call, Response<TMDBApiResponse> response) {
                data.setValue(response.body().getResults());
                Log.d("NAKAi", data.getValue().toString());
            }

            @Override
            public void onFailure(Call<TMDBApiResponse> call, Throwable t) {
                // TODO Improve error handling
                data.setValue(null);
                Log.d("NAKAe", "error" + t.toString());
            }
        });

        return data;
    }
}
