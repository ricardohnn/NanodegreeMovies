package com.rdzero.popularmovies.view.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.databinding.MoviesListItemBinding;
import com.rdzero.popularmovies.service.model.MoviesDetails;

/**
 * Created by ricardo.nakayama on 13/09/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    public MoviesAdapter() {
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
        MoviesDetails moviesDetails = get
        holder.binding.setMovieDetails(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        final MoviesListItemBinding binding;

        public MovieViewHolder(MoviesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Item item) {
            binding.setMovieDetails(item);
            binding.executePendingBindings();
        }
    }
}
