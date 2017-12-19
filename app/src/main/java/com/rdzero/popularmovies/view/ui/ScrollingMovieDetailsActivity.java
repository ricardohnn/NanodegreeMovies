package com.rdzero.popularmovies.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityScrollingMovieDetailsBinding;
import com.rdzero.popularmovies.service.localDB.FavoriteMovieDataManager;
import com.rdzero.popularmovies.service.localDB.FavoriteMoviesContract;
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

public class ScrollingMovieDetailsActivity extends AppCompatActivity {

    private MovieDetails movieDetails;
    private MovieReviewsAdapter movieReviewsAdapter;
    private MovieTrailersAdapter movieTrailersAdapter;
    private Context context;

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
    private final MovieTrailerClickCallback trailerClickCallback = new MovieTrailerClickCallback() {
        @Override
        public void onTrailerClick(MovieTrailers movieTrailers) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                watchYoutubeVideo(context, movieTrailers.getId());
                ContentValues initialValues = new ContentValues();
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_ID, movieDetails.getId());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_TITLE, movieDetails.getTitle());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_ORIGINAL_TITLE, movieDetails.getOriginalTitle());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_POSTER_PATH, movieDetails.getPosterPath());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_OVERVIEW, movieDetails.getOverview());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_RELEASE_DATE, movieDetails.getReleaseDate());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_POPULARITY, movieDetails.getPopularity());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_VOTE_AVERAGE, movieDetails.getVoteAverage());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_VOTE_COUNT, movieDetails.getVoteCount());
                initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_FAVORITE, true);

                Uri contentUri = Uri.withAppendedPath(FavoriteMovieDataManager.CONTENT_URI, FavoriteMoviesContract.MovieFavoriteEntry.TABLE_NAME);
                Uri resultUri = context.getContentResolver().insert(contentUri, initialValues);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Bundle bundle = getIntent().getExtras();
        movieDetails = bundle.getParcelable("movieDetails");

        MovieReviewsViewModel reviewsViewModel = ViewModelProviders.of(this, new MovieReviewsViewModelFactory(this.getApplication(), movieDetails.getId())).get(MovieReviewsViewModel.class);
        observeReviewsViewModel(reviewsViewModel);

        MovieTrailersViewModel movieTrailersViewModel = ViewModelProviders.of(this, new MovieTrailersViewModelFactory(this.getApplication(), movieDetails.getId())).get(MovieTrailersViewModel.class);
        observeTrailersViewModel(movieTrailersViewModel);

        ActivityScrollingMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling_movie_details);
        binding.setMovieDetails(movieDetails);
        binding.movieDetailContentScrollingInclude.setMovieDetails(movieDetails);

        RecyclerView movieReviewsView = findViewById(R.id.movie_reviews_list);

        movieReviewsAdapter = new MovieReviewsAdapter();
        movieReviewsView.setAdapter(movieReviewsAdapter);

        RecyclerView movieTrailersView = findViewById(R.id.movie_trailers_list);

        movieTrailersAdapter = new MovieTrailersAdapter(trailerClickCallback);
        movieTrailersView.setAdapter(movieTrailersAdapter);


        Toolbar toolbar = findViewById(R.id.movie_detail_toolbar);

        setSupportActionBar(toolbar);
    }

    private void observeReviewsViewModel(MovieReviewsViewModel viewModel) {
        viewModel.getMovieReviewsObservable(movieDetails.getId()).observe(this, new Observer<List<MovieReviews>>() {
            @Override
            public void onChanged(@Nullable List<MovieReviews> movieReviewsList) {
                if (movieReviewsList != null) {
                    movieReviewsAdapter.setMovieReviewsList(movieReviewsList);
                }
            }
        });
    }

    private void observeTrailersViewModel(MovieTrailersViewModel viewModel) {
        viewModel.getMovieTrailersObservable(movieDetails.getId()).observe(this, new Observer<List<MovieTrailers>>() {
            @Override
            public void onChanged(@Nullable List<MovieTrailers> movieTrailersList) {
                if (movieTrailersList != null) {
                    movieTrailersAdapter.setMovieTrailersList(movieTrailersList);
                }
            }
        });
    }
}
