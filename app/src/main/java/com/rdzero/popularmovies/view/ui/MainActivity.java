package com.rdzero.popularmovies.view.ui;


import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.MoviesListBinding;
import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.model.MoviesDetails;
import com.rdzero.popularmovies.view.adapter.MoviesAdapter;
import com.rdzero.popularmovies.viewmodel.MoviesViewModel;

import java.util.List;


public class MainActivity extends LifecycleActivity {

    private MoviesListBinding moviesListBinding;
    private MoviesAdapter moviesAdapter;
    private List<MoviesDetails> moviesDetailsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MoviesViewModel viewModel;

        moviesListBinding = DataBindingUtil.setContentView(this, R.layout.movies_list);

        moviesAdapter = new MoviesAdapter();
        moviesListBinding.moviesList.setAdapter(moviesAdapter);

        moviesListBinding.setIsLoading(true);

        viewModel = ViewModelProviders.of(this, new MoviesViewModel.MoviesViewModelFactory(getApplication(), "popular")).get(MoviesViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(MoviesViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getMoviesObservable().observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(@Nullable Movies movies) {
                if (movies != null) {
                    moviesDetailsList = movies.getResults();
                    moviesListBinding.setIsLoading(false);
                    moviesAdapter.setMoviesDetailsList(moviesDetailsList);
                }
            }
        });
    }
}
