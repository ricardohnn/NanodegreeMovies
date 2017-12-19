package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rdzero.popularmovies.service.model.MovieDetails;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel{
    private LiveData<List<MovieDetails>> moviesObservable;

    public MovieDetailsViewModel(Application application) {
        super(application);
        moviesObservable = MoviesRepository.getInstance().getMoviesList("popular");
    }

    public LiveData<List<MovieDetails>> getMovieDetailsObservable(String searchType){
        if (searchType != null && !("".equals(searchType)))
            moviesObservable = MoviesRepository.getInstance().getMoviesList(searchType);
        return moviesObservable;
    }
}
