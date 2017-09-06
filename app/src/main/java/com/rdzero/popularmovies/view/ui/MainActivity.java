package com.rdzero.popularmovies.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rdzero.popularmovies.BuildConfig;
import com.rdzero.popularmovies.R;

public class MainActivity extends AppCompatActivity {

    //TODO Example code to call api key - https://api.themoviedb.org/3/movie/550?api_key=
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
