package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rdzero.popularmovies.service.model.MovieTrailers;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

import java.util.List;

public class MovieTrailersViewModel extends AndroidViewModel{
    private LiveData<List<MovieTrailers>> movieTrailersObservable;

    public MovieTrailersViewModel(Application application, int movieId) {
        super(application);
        movieTrailersObservable = MoviesRepository.getInstance().getMovieTrailers(movieId);
    }

    public LiveData<List<MovieTrailers>> getMovieTrailersObservable(int movieId){
        if (movieId > -1)
            movieTrailersObservable = MoviesRepository.getInstance().getMovieTrailers(movieId);
        return movieTrailersObservable;
    }
}
