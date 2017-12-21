package com.rdzero.popularmovies.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityScrollingMovieDetailsBinding;
import com.rdzero.popularmovies.databinding.ActivityScrollingMovieFavoriteDetailsBinding;
import com.rdzero.popularmovies.service.model.MovieDetails;
import com.rdzero.popularmovies.service.model.MovieReviews;
import com.rdzero.popularmovies.service.model.MovieTrailers;
import com.rdzero.popularmovies.view.adapter.MovieReviewsAdapter;
import com.rdzero.popularmovies.view.adapter.MovieTrailersAdapter;
import com.rdzero.popularmovies.view.callback.MovieTrailerClickCallback;
import com.rdzero.popularmovies.viewmodel.MovieReviewsViewModel;
import com.rdzero.popularmovies.viewmodel.MovieReviewsViewModelFactory;
import com.rdzero.popularmovies.viewmodel.MovieTrailersViewModel;
import com.rdzero.popularmovies.viewmodel.MovieTrailersViewModelFactory;

import java.util.List;

import static com.rdzero.popularmovies.service.localDB.FavoriteMovieDataManager.insertFavoriteMovie;

public class ScrollingMovieFavoriteDetailsActivity extends AppCompatActivity {

    private MovieDetails movieDetails;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Bundle bundle = getIntent().getExtras();
        movieDetails = bundle.getParcelable("movieDetails");

        ActivityScrollingMovieFavoriteDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling_movie_favorite_details);
        binding.setMovieDetails(movieDetails);
        binding.movieFavoriteDetailContentScrollingInclude.setMovieDetails(movieDetails);

        Toolbar toolbar = findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
    }
}
