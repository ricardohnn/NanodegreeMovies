package com.rdzero.popularmovies.view.ui;


import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityMainBinding;
import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.viewmodel.MoviesViewModel;

public class MainActivity extends LifecycleActivity {

    private ActivityMainBinding activityMainBinding;
    private LiveData<Movies> moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        activityMainBinding.setIsLoading(true);
        moviesViewModel = new MoviesViewModel("popular").getMoviesObservable();

    }

    @Override
    protected void onStart() {
        super.onStart();

        moviesViewModel.observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(@Nullable Movies movies) {
                if (movies != null) {
                    activityMainBinding.setIsLoading(false);
                    activityMainBinding.setMovies(movies);
                }
            }
        });
    }
}
