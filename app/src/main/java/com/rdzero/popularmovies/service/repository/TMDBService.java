package com.rdzero.popularmovies.service.repository;


import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.model.MoviesDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ricardo.nakayama on 11/09/2017.
 */

interface TMDBService {
    String HTTPS_API_TMDB_URL = "https://api.themoviedb.org/3/";

    @GET("movie/{type}")
    Call<List<Movies>> getMoviesList(@Path("type") String searchType);

    @GET("movie/{movie_id}")
    Call<MoviesDetails> getMovieDetails(@Path("movie_id") String movieId);
}