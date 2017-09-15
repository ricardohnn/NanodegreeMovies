package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

/**
 * Created by ricardo.nakayama on 13/09/2017.
 */

public class MoviesViewModel extends AndroidViewModel {
    private final LiveData<Movies> moviesObservable;

    private final String searchType;

    public MoviesViewModel(@NonNull Application application,
                           final String searchType) {
        super(application);
        this.searchType = searchType;

        moviesObservable = MoviesRepository.getInstance().getMoviesList(searchType);
    }

    public  LiveData<Movies> getMoviesObservable(){
        return moviesObservable;
    }

    public static class MoviesViewModelFactory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final String searchType;

        public MoviesViewModelFactory(@NonNull Application application, String searchType) {
            this.application = application;
            this.searchType = searchType;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MoviesViewModel(application, searchType);
        }
    }

}
