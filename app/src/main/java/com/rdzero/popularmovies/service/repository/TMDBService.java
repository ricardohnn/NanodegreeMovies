package com.rdzero.popularmovies.service.repository;


import com.rdzero.popularmovies.service.model.DetailsResponse;
import com.rdzero.popularmovies.service.model.ReviewsResponse;
import com.rdzero.popularmovies.service.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface TMDBService {
    String HTTPS_API_TMDB_URL = "https://api.themoviedb.org/3/";

    @GET("movie/{type}")
    Call<DetailsResponse> getMoviesList(@Path("type") String searchType);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewsResponse> getMovieReviews(@Path("movie_id") String movieId);

    @GET("movie/{movie_id}/videos")
    Call<TrailersResponse> getMovieTrailers(@Path("movie_id") String movieId);
}
