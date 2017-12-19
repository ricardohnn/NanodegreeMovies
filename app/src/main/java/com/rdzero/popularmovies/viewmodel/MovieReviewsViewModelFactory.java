package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

@SuppressWarnings("unchecked")
public class MovieReviewsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private int mMovieId;

    public MovieReviewsViewModelFactory(Application application, int movieId) {
        mApplication = application;
        mMovieId = movieId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MovieReviewsViewModel(mApplication, mMovieId);
    }
}