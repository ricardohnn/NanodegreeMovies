package com.rdzero.popularmovies.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

/**
 * Created by ricardo.nakayama on 13/09/2017.
 */

public class MoviesViewModel extends ViewModel {
    private final LiveData<Movies> moviesObservable;

    public MoviesViewModel(String searchType) {
        moviesObservable = MoviesRepository.getInstance().getMoviesList(searchType);
    }

    public  LiveData<Movies> getMoviesObservable(){
        return moviesObservable;
    }
}
