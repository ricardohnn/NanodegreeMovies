package com.rdzero.popularmovies.view.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
/**
 * Created by ricardo.nakayama on 9/18/2017.
 */

public final class DataBinder {

    //TODO Use the configuration from API to retrieve the correct size and url path
    private static final String HTTPS_API_TMDB_IMAGES_URL = "https://image.tmdb.org/t/p/w185";

    private DataBinder() {
    }

    @BindingAdapter({"app:imageUrl", "app:placeholder"})
    public static void setImageUrl(ImageView imageView, String url, Drawable placeholder) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(HTTPS_API_TMDB_IMAGES_URL + url)
                .placeholder(placeholder)
                .into(imageView);
    }
}