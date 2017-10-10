package com.rdzero.popularmovies.view.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public final class MoviePosterDataBinder {

    //TODO Use the configuration from API to retrieve the correct size and url path
    private static final String HTTPS_API_TMDB_IMAGES_URL = "https://image.tmdb.org/t/p/w185";

    private MoviePosterDataBinder() {
    }

    @BindingAdapter({"app:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(HTTPS_API_TMDB_IMAGES_URL + url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}