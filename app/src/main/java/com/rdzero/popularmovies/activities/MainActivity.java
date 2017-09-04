package com.rdzero.popularmovies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rdzero.popularmovies.BuildConfig;
import com.rdzero.popularmovies.R;

public class MainActivity extends AppCompatActivity {

    //TODO Example code to call api key - https://api.themoviedb.org/3/movie/550?api_key=c33e30d99d9e89652690890b0cdee8da
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
