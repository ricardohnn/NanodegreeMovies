package com.rdzero.popularmovies.view.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityScrollingMovieDetailBinding;
import com.rdzero.popularmovies.service.model.MoviesDetails;

public class ScrollingMovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        MoviesDetails moviesDetails;
        moviesDetails = bundle.getParcelable("movieDetails");

        ActivityScrollingMovieDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_scrolling_movie_detail);
        binding.setMovieDetails(moviesDetails);
        binding.movieDetailContentScrollingInclude.setMovieDetails(moviesDetails);

        Toolbar toolbar = findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
    }
}
