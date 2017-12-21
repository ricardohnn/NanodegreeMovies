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

import static com.rdzero.popularmovies.service.localDB.FavoriteMovieDataManager.getFavoriteMoviesList;

public class MoviesListMainActivity extends LifecycleActivity {

    private ActivityMainMoviesListBinding moviesListBinding;
    private MoviesAdapter moviesAdapter;
    private MovieDetailsViewModel viewModel;
    private final static String MOVIE_LIST_TYPE = "movie_list_type";
    private final static int TYPE_TOP_RATED = 0;
    private final static int TYPE_POPULAR = 1;
    private final static int TYPE_FAVORITE = 2;
    private int movie_list_type = TYPE_POPULAR;
    private final static String TOP_RATED = "top_rated";
    private final static String POPULAR = "popular";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (savedInstanceState != null){
            movie_list_type = savedInstanceState.getInt(MOVIE_LIST_TYPE);
        } else {
            movie_list_type = TYPE_POPULAR;
        }

        moviesListBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_movies_list);

        moviesAdapter = new MoviesAdapter(projectClickCallback);
        moviesListBinding.moviesList.setAdapter(moviesAdapter);
        moviesListBinding.setIsLoading(true);

        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);

        if (movie_list_type == TYPE_FAVORITE){
            List<MovieDetails> movieDetailsList = getFavoriteMoviesList(this);
            if (movieDetailsList != null) {
                moviesAdapter.setMoviesDetailsList(movieDetailsList);
                moviesListBinding.setIsLoading(false);
            }
        } else{
            observeViewModel(viewModel);
        }
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

    private void loadFavoriteMovies(){
        List<MovieDetails> movieDetailsList = getFavoriteMoviesList(this);
        if (movieDetailsList != null) {
            moviesAdapter.setMoviesDetailsList(movieDetailsList);
            moviesListBinding.setIsLoading(false);
        }
    }

    private final MovieClickCallback projectClickCallback = new MovieClickCallback() {
        @Override
        public void onClick(MovieDetails movieDetails) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Intent intent;
                if(movie_list_type == TYPE_FAVORITE)
                    intent = new Intent(MoviesListMainActivity.this, ScrollingMovieFavoriteDetailsActivity.class);
                else
                    intent = new Intent(MoviesListMainActivity.this, ScrollingMovieDetailsActivity.class);
                intent.putExtra("movieDetails", movieDetails);
                context.startActivity(intent);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_search_type_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_rated_item:
                moviesListBinding.setIsLoading(true);
                movie_list_type = TYPE_TOP_RATED;
                searchNewObservableViewModel(viewModel, TOP_RATED);
                break;
            case R.id.popular_item:
                moviesListBinding.setIsLoading(true);
                movie_list_type = TYPE_POPULAR;
                searchNewObservableViewModel(viewModel, POPULAR);
                break;
            case R.id.favorite_item:
                moviesListBinding.setIsLoading(true);
                movie_list_type = TYPE_FAVORITE;
                loadFavoriteMovies();
                break;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(MOVIE_LIST_TYPE, movie_list_type);
        super.onSaveInstanceState(savedInstanceState);
    }
}
