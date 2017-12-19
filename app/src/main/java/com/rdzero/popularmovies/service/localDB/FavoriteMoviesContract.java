package com.rdzero.popularmovies.service.localDB;

/**
 * Created by ricardo.nakayama on 12/19/2017.
 */

import android.net.Uri;
import android.provider.BaseColumns;

public final class FavoriteMoviesContract {

    public static final String AUTHORITY = "com.rdzero.popularmovies";
    private static final String SCHEME = "content://";
    private static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY);

    public FavoriteMoviesContract() {
    }

    public static class MovieFavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite_movies";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_FAVORITE = "favorite";

        public static final Uri URI = CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    }
}