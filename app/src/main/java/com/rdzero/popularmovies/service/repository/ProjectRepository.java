package com.rdzero.popularmovies.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rdzero.popularmovies.BuildConfig;
import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.model.MoviesDetails;

import java.io.IOException;
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

/**
 * Created by ricardo.nakayama on 11/09/2017.
 */

public class ProjectRepository {
    private TMDBService tmdbService;
    private static ProjectRepository projectRepository;

    private ProjectRepository(){
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

    public synchronized static ProjectRepository getInstance() {
        //TODO Use Dagger here
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository;
    }

    public LiveData<List<Movies>> getMoviesList(String searchType) {
        final MutableLiveData<List<Movies>> data = new MutableLiveData<>();

        tmdbService.getMoviesList(searchType).enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                // TODO Improve error handling
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<MoviesDetails> getMovieDetails(String movieId) {
        final MutableLiveData<MoviesDetails> data = new MutableLiveData<>();

        tmdbService.getMovieDetails(movieId).enqueue(new Callback<MoviesDetails>() {
            @Override
            public void onResponse(Call<MoviesDetails> call, Response<MoviesDetails> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MoviesDetails> call, Throwable t) {
                // TODO Improve error handling
                data.setValue(null);
            }
        });

        return data;
    }
}
