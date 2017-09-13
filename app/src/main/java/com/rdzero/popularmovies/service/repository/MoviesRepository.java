package com.rdzero.popularmovies.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.rdzero.popularmovies.BuildConfig;
import com.rdzero.popularmovies.service.model.Movies;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ricardo.nakayama on 11/09/2017.
 */

public class MoviesRepository {
    private TMDBService tmdbService;
    private static MoviesRepository moviesRepository;

    private MoviesRepository(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TMDBService.HTTPS_API_TMDB_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tmdbService = retrofit.create(TMDBService.class);
    }

    public synchronized static MoviesRepository getInstance() {
        //TODO Use Dagger here
        if (moviesRepository == null) {
            moviesRepository = new MoviesRepository();
        }
        return moviesRepository;
    }

    public LiveData<Movies> getMoviesList(String searchType) {
        final MutableLiveData<Movies> data = new MutableLiveData<>();

        tmdbService.getMoviesList(searchType).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d("NAKA", response.body().toString());
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                // TODO Improve error handling
                Log.d("NAKA", t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }
}
