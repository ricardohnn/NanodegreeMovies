package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

public class MoviesViewModel extends AndroidViewModel{
    private LiveData<Movies> moviesObservable;

    public MoviesViewModel(Application application) {
        super(application);
        moviesObservable = MoviesRepository.getInstance().getMoviesList("popular");
    }

    public LiveData<Movies> getMoviesObservable(String searchType){
        moviesObservable = MoviesRepository.getInstance().getMoviesList(searchType);
        return moviesObservable;
    }

    public LiveData<Movies> getMoviesObservable(){
        return moviesObservable;
    }
}
