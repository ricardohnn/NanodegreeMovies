package com.rdzero.popularmovies.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityScrollingMovieDetailsReviewItemBinding;
import com.rdzero.popularmovies.databinding.ActivityScrollingMovieDetailsTrailerItemBinding;
import com.rdzero.popularmovies.service.model.MovieReviews;
import com.rdzero.popularmovies.service.model.MovieTrailers;
import com.rdzero.popularmovies.view.callback.MovieClickCallback;
import com.rdzero.popularmovies.view.callback.MovieTrailerClickCallback;
import com.rdzero.popularmovies.viewmodel.MovieTrailersViewModel;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailersAdapter.MovieTrailersViewModel> {

    private List<? extends MovieTrailers> movieTrailersList;

    @Nullable
    private final MovieTrailerClickCallback movieTrailerClickCallback;

    public MovieTrailersAdapter(@Nullable MovieTrailerClickCallback movieTrailerClickCallback) {
        this.movieTrailerClickCallback = movieTrailerClickCallback;
    }

    public void setMovieTrailersList(final List<? extends MovieTrailers> movieTrailersList) {
        if (this.movieTrailersList == null) {
            this.movieTrailersList = movieTrailersList;
            notifyItemRangeInserted(0, movieTrailersList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MovieTrailersAdapter.this.movieTrailersList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieTrailersList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MovieTrailersAdapter.this.movieTrailersList.get(oldItemPosition).getId() ==
                            movieTrailersList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MovieTrailers movieTrailers = movieTrailersList.get(newItemPosition);
                    MovieTrailers old = movieTrailersList.get(oldItemPosition);
                    return (movieTrailers.getId() == old.getId());
                }
            });
            this.movieTrailersList = movieTrailersList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public MovieTrailersViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityScrollingMovieDetailsTrailerItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.activity_scrolling_movie_details_trailer_item,
                        parent, false);

        binding.setTrailerCallback(movieTrailerClickCallback);

        return new MovieTrailersViewModel(binding);
    }

    @Override
    public void onBindViewHolder(MovieTrailersViewModel holder, int position) {
        holder.binding.setMovieTrailers(movieTrailersList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return movieTrailersList == null ? 0 : movieTrailersList.size();
    }

    class MovieTrailersViewModel extends RecyclerView.ViewHolder {

        final ActivityScrollingMovieDetailsTrailerItemBinding binding;

        public MovieTrailersViewModel(ActivityScrollingMovieDetailsTrailerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
