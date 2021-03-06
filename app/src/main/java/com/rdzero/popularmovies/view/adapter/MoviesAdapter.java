package com.rdzero.popularmovies.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.ActivityMainMoviesListItemBinding;
import com.rdzero.popularmovies.service.model.MovieDetails;
import com.rdzero.popularmovies.view.callback.MovieClickCallback;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<? extends MovieDetails> moviesDetailsList;

    @Nullable
    private final MovieClickCallback movieClickCallback;

    public MoviesAdapter(@Nullable MovieClickCallback movieClickCallback) {
        this.movieClickCallback = movieClickCallback;
    }

    public void setMoviesDetailsList(final List<? extends MovieDetails> moviesDetailsList) {
        if (this.moviesDetailsList == null) {
            this.moviesDetailsList = moviesDetailsList;
            notifyItemRangeInserted(0, moviesDetailsList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MoviesAdapter.this.moviesDetailsList.size();
                }

                @Override
                public int getNewListSize() {
                    return moviesDetailsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MoviesAdapter.this.moviesDetailsList.get(oldItemPosition).getId() ==
                            moviesDetailsList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    try {
                        MovieDetails movieDetails = moviesDetailsList.get(newItemPosition);
                        MovieDetails old = moviesDetailsList.get(oldItemPosition);
                        return (movieDetails.getId() == old.getId());
                    } catch (IndexOutOfBoundsException e) {
                        return false;
                    }
                }
            });
            this.moviesDetailsList = moviesDetailsList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityMainMoviesListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.activity_main_movies_list_item,
                        parent, false);

        binding.setCallback(movieClickCallback);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.binding.setMovieDetails(moviesDetailsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return moviesDetailsList == null ? 0 : moviesDetailsList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        final ActivityMainMoviesListItemBinding binding;

        private MovieViewHolder(ActivityMainMoviesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
