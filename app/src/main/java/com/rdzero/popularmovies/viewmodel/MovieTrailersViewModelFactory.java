package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

@SuppressWarnings("unchecked")
public class MovieTrailersViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private int mMovieId;

    public MovieTrailersViewModelFactory(Application application, int movieId) {
        mApplication = application;
        mMovieId = movieId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MovieTrailersViewModel(mApplication, mMovieId);
    }
}