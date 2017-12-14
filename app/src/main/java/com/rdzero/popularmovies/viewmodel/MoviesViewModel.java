package com.rdzero.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rdzero.popularmovies.service.model.MovieReviews;
import com.rdzero.popularmovies.service.model.MoviesDetails;
import com.rdzero.popularmovies.service.model.MovieTrailers;
import com.rdzero.popularmovies.service.repository.MoviesRepository;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel{
    private LiveData<List<MoviesDetails>> moviesObservable;

    public MoviesViewModel(Application application) {
        super(application);
        moviesObservable = MoviesRepository.getInstance().getMoviesList("popular");
    }

    public LiveData<List<MoviesDetails>> getMoviesObservable(String searchType){
        if (searchType != null && !("".equals(searchType)))
            moviesObservable = MoviesRepository.getInstance().getMoviesList(searchType);
        return moviesObservable;
    }
}
