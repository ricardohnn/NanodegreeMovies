package com.rdzero.popularmovies.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.MoviesListItemBinding;
import com.rdzero.popularmovies.service.model.MoviesDetails;

import java.util.List;

/**
 * Created by ricardo.nakayama on 13/09/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    List<? extends MoviesDetails> moviesDetailsList;

    public MoviesAdapter() {
    }

    public void setMoviesDetailsList(final List<? extends MoviesDetails> moviesDetailsList) {
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
                    MoviesDetails moviesDetails = moviesDetailsList.get(newItemPosition);
                    MoviesDetails old = moviesDetailsList.get(oldItemPosition);
                    return (moviesDetails.getId() == old.getId());
                }
            });
            this.moviesDetailsList = moviesDetailsList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MoviesListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.movies_list_item,
                        parent, false);

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

        final MoviesListItemBinding binding;

        public MovieViewHolder(MoviesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
