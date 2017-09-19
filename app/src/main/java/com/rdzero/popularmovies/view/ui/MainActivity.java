package com.rdzero.popularmovies.view.ui;


import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

    private MoviesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("NAKA", "onCreate");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_search_type_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MoviesViewModel model;
        switch (item.getItemId()){
            case R.id.top_rated_item:
                if (item.isChecked()) {
                    item.setChecked(false);
                    model = ViewModelProviders.of(this, new MoviesViewModel.MoviesViewModelFactory(getApplication(), "popular")).get(MoviesViewModel.class);
                } else {
                    item.setChecked(true);
                    model = ViewModelProviders.of(this, new MoviesViewModel.MoviesViewModelFactory(getApplication(), "top_rated")).get(MoviesViewModel.class);
                }
                observeViewModel(model);
        }
        return true;
    }
}
