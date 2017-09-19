package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

/**
 * Created by ricardo.nakayama on 13/09/2017.
 */

public class MoviesViewModel extends AndroidViewModel{
    private LiveData<Movies> moviesObservable;
    public MoviesViewModel(Application application) {
        super(application);
    }

    public LiveData<Movies> getMoviesObservable(String searchType){
        moviesObservable = MoviesRepository.getInstance().getMoviesList(searchType);
        return moviesObservable;
    }

    public LiveData<Movies> getMoviesObservable(){
        return moviesObservable;
    }
}
