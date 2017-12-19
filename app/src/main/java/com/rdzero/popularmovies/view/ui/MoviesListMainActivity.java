package com.rdzero.popularmovies.view.ui;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityMainMoviesListBinding;
import com.rdzero.popularmovies.service.model.MovieDetails;
import com.rdzero.popularmovies.view.adapter.MoviesAdapter;
import com.rdzero.popularmovies.view.callback.MovieClickCallback;
import com.rdzero.popularmovies.viewmodel.MovieDetailsViewModel;

import java.util.List;

public class MoviesListMainActivity extends LifecycleActivity {

    private ActivityMainMoviesListBinding moviesListBinding;
    private MoviesAdapter moviesAdapter;
    private MovieDetailsViewModel viewModel;
    private boolean topRatedSelected = false;
    private final static String TOP_RATED = "top_rated";
    private final static String POPULAR = "popular";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (savedInstanceState != null){
            topRatedSelected = savedInstanceState.getBoolean(TOP_RATED);
        }

        moviesListBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_movies_list);

        moviesAdapter = new MoviesAdapter(projectClickCallback);
        moviesListBinding.moviesList.setAdapter(moviesAdapter);
        moviesListBinding.setIsLoading(true);

        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(MovieDetailsViewModel viewModel) {
        viewModel.getMovieDetailsObservable(null).observe(this, new Observer<List<MovieDetails>>() {
            @Override
            public void onChanged(@Nullable List<MovieDetails> movieDetailsList) {
                if (movieDetailsList != null) {
                    moviesAdapter.setMoviesDetailsList(movieDetailsList);
                    moviesListBinding.setIsLoading(false);
                }
            }
        });
    }

    private void searchNewObservableViewModel(MovieDetailsViewModel viewModel, String searchType) {
        viewModel.getMovieDetailsObservable(searchType).observe(this, new Observer<List<MovieDetails>>() {
            @Override
            public void onChanged(@Nullable List<MovieDetails> movieDetailsList) {
                if (movieDetailsList != null) {
                    moviesAdapter.setMoviesDetailsList(movieDetailsList);
                    moviesListBinding.setIsLoading(false);
                }
            }
        });
    }

    private final MovieClickCallback projectClickCallback = new MovieClickCallback() {
        @Override
        public void onClick(MovieDetails movieDetails) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Intent intent = new Intent(MoviesListMainActivity.this, ScrollingMovieDetailsActivity.class);
                intent.putExtra("movieDetails", movieDetails);
                context.startActivity(intent);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_search_type_menu,menu);
        if (topRatedSelected){
            MenuItem menuItem = menu.findItem(R.id.top_rated_item);
            menuItem.setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_rated_item:
                moviesListBinding.setIsLoading(true);
                if (item.isChecked()) {
                    item.setChecked(false);
                    topRatedSelected = false;
                    searchNewObservableViewModel(viewModel, POPULAR);
                } else {
                    item.setChecked(true);
                    topRatedSelected = true;
                    searchNewObservableViewModel(viewModel, TOP_RATED);
                }
                break;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(TOP_RATED, topRatedSelected);
        super.onSaveInstanceState(savedInstanceState);
    }
}
