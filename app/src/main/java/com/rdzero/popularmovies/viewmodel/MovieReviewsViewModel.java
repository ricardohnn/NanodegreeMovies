package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rdzero.popularmovies.service.model.MovieReviews;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

import java.util.List;

public class MovieReviewsViewModel extends AndroidViewModel{
    private LiveData<List<MovieReviews>> movieReviewsObservable;

    public MovieReviewsViewModel(Application application, int movieId) {
        super(application);
        movieReviewsObservable = MoviesRepository.getInstance().getMovieReviews(movieId);
    }

    public LiveData<List<MovieReviews>> getMovieReviewsObservable(int movieId){
        if (movieId > -1)
            movieReviewsObservable = MoviesRepository.getInstance().getMovieReviews(movieId);
        return movieReviewsObservable;
    }
}