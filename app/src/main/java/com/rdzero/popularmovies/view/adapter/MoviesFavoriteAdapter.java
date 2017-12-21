package com.rdzero.popularmovies.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityMainMoviesFavoriteListItemBinding;
import com.rdzero.popularmovies.databinding.ActivityMainMoviesListItemBinding;
import com.rdzero.popularmovies.service.model.MovieDetails;
import com.rdzero.popularmovies.view.callback.MovieClickCallback;

import java.util.List;

public class MoviesFavoriteAdapter extends RecyclerView.Adapter<MoviesFavoriteAdapter.MovieFavoriteViewHolder> {

    private List<? extends MovieDetails> moviesDetailsList;

    public void setMoviesFavoriteList(final List<? extends MovieDetails> moviesDetailsList) {
        if (this.moviesDetailsList == null) {
            this.moviesDetailsList = moviesDetailsList;
            notifyItemRangeInserted(0, moviesDetailsList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MoviesFavoriteAdapter.this.moviesDetailsList.size();
                }

                @Override
                public int getNewListSize() {
                    return moviesDetailsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MoviesFavoriteAdapter.this.moviesDetailsList.get(oldItemPosition).getId() ==
                            moviesDetailsList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MovieDetails movieDetails = moviesDetailsList.get(newItemPosition);
                    MovieDetails old = moviesDetailsList.get(oldItemPosition);
                    return (movieDetails.getId() == old.getId());
                }
            });
            this.moviesDetailsList = moviesDetailsList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public MovieFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityMainMoviesFavoriteListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.activity_main_movies_favorite_list_item,
                        parent, false);

        return new MovieFavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieFavoriteViewHolder holder, int position) {
        holder.binding.setMovieDetails(moviesDetailsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return moviesDetailsList == null ? 0 : moviesDetailsList.size();
    }

    class MovieFavoriteViewHolder extends RecyclerView.ViewHolder {

        final ActivityMainMoviesFavoriteListItemBinding binding;

        private MovieFavoriteViewHolder(ActivityMainMoviesFavoriteListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
