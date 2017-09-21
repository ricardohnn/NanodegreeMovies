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
import com.rdzero.popularmovies.databinding.MoviesListBinding;
import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.model.MoviesDetails;
import com.rdzero.popularmovies.view.adapter.MoviesAdapter;
import com.rdzero.popularmovies.view.callback.MovieClickCallback;
import com.rdzero.popularmovies.viewmodel.MoviesViewModel;

import java.util.List;

public class MainActivity extends LifecycleActivity {

    private MoviesListBinding moviesListBinding;
    private MoviesAdapter moviesAdapter;
    private List<MoviesDetails> moviesDetailsList;
    private MoviesViewModel viewModel;
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

        moviesListBinding = DataBindingUtil.setContentView(this, R.layout.movies_list);

        moviesAdapter = new MoviesAdapter(projectClickCallback);
        moviesListBinding.moviesList.setAdapter(moviesAdapter);
        moviesListBinding.setIsLoading(true);

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(MoviesViewModel viewModel) {
        viewModel.getMoviesObservable().observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(@Nullable Movies movies) {
                if (movies != null) {
                    moviesDetailsList = movies.getResults();
                    moviesAdapter.setMoviesDetailsList(moviesDetailsList);
                    moviesListBinding.setIsLoading(false);
                }
            }
        });
    }

    private void searchNewObservableViewModel(MoviesViewModel viewModel, String searchType) {
        viewModel.getMoviesObservable(searchType).observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(@Nullable Movies movies) {
                if (movies != null) {
                    moviesDetailsList = movies.getResults();
                    moviesAdapter.setMoviesDetailsList(moviesDetailsList);
                    moviesListBinding.setIsLoading(false);
                }
            }
        });
    }

    private final MovieClickCallback projectClickCallback = new MovieClickCallback() {
        @Override
        public void onClick(MoviesDetails moviesDetails) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Intent intent = new Intent(MainActivity.this, ScrollingMovieDetailActivity.class);
                intent.putExtra("movieDetails", moviesDetails);
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
