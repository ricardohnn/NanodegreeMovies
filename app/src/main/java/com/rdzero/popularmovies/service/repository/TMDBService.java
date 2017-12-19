package com.rdzero.popularmovies.service.repository;


import com.rdzero.popularmovies.service.model.MovieTrailersResponse;
import com.rdzero.popularmovies.service.model.MovieDetailsResponse;
import com.rdzero.popularmovies.service.model.MovieReviewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface TMDBService {
    String HTTPS_API_TMDB_URL = "https://api.themoviedb.org/3/";

    @GET("movie/{type}")
    Call<MovieDetailsResponse> getMoviesList(@Path("type") String searchType);

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviewsResponse> getMovieReviews(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/videos")
    Call<MovieTrailersResponse> getMovieTrailers(@Path("movie_id") int movieId);
}
