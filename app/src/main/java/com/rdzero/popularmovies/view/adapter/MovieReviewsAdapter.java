package com.rdzero.popularmovies.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityScrollingMovieDetailsReviewItemBinding;
import com.rdzero.popularmovies.service.model.MovieReviews;

import java.util.List;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewViewHolder> {

    private List<? extends MovieReviews> movieReviewsList;

    public MovieReviewsAdapter() {
    }

    public void setMovieReviewsList(final List<? extends MovieReviews> movieReviewsList) {
        if (this.movieReviewsList == null) {
            this.movieReviewsList = movieReviewsList;
            notifyItemRangeInserted(0, movieReviewsList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MovieReviewsAdapter.this.movieReviewsList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieReviewsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MovieReviewsAdapter.this.movieReviewsList.get(oldItemPosition).getId() ==
                            movieReviewsList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MovieReviews movieReviews = movieReviewsList.get(newItemPosition);
                    MovieReviews old = movieReviewsList.get(oldItemPosition);
                    return (movieReviews.getId() == old.getId());
                }
            });
            this.movieReviewsList = movieReviewsList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public MovieReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityScrollingMovieDetailsReviewItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.activity_scrolling_movie_details_review_item,
                        parent, false);

        //binding.setCallback(movieClickCallback);

        return new MovieReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieReviewViewHolder holder, int position) {
        holder.binding.setMovieReviews(movieReviewsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return movieReviewsList == null ? 0 : movieReviewsList.size();
    }

    class MovieReviewViewHolder extends RecyclerView.ViewHolder {

        final ActivityScrollingMovieDetailsReviewItemBinding binding;

        public MovieReviewViewHolder(ActivityScrollingMovieDetailsReviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
