package com.rdzero.popularmovies.service.repository;


import com.rdzero.popularmovies.service.model.MoviesDetails;
import com.rdzero.popularmovies.service.model.TMDBApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface TMDBService {
    String HTTPS_API_TMDB_URL = "https://api.themoviedb.org/3/";

    @GET("movie/{type}")
    Call<TMDBApiResponse<MoviesDetails>> getMoviesList(@Path("type") String searchType);

    @GET("movie/{movie_id}/reviews")
    Call<TMDBApiResponse> getMovieReviews(@Path("movie_id") String movieId);

    @GET("movie/{movie_id}/videos")
    Call<TMDBApiResponse> getMovieTrailers(@Path("movie_id") String movieId);
}
