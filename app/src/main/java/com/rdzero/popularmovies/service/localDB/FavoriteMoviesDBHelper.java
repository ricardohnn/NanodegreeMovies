package com.rdzero.popularmovies.service.localDB;

/**
 * Created by ricardo.nakayama on 12/19/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.rdzero.popularmovies.service.localDB.FavoriteMoviesContract.*;

public class FavoriteMoviesDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorite_movies.db";
    private static final int DATABASE_VERSION = 1;

    public FavoriteMoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        final String SQL_CREATE_FAVORITE_MOVIES_TABLE = "CREATE TABLE " + MovieFavoriteEntry.TABLE_NAME + " (" +
                MovieFavoriteEntry.COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                MovieFavoriteEntry.COLUMN_TITLE + " STRING NOT NULL, " +
                MovieFavoriteEntry.COLUMN_ORIGINAL_TITLE + " STRING NOT NULL, " +
                MovieFavoriteEntry.COLUMN_POSTER_PATH + " STRING NOT NULL, " +
                MovieFavoriteEntry.COLUMN_BACKDROP_PATH + " STRING NOT NULL, " +
                MovieFavoriteEntry.COLUMN_OVERVIEW + " STRING NOT NULL, " +
                MovieFavoriteEntry.COLUMN_RELEASE_DATE + " STRING NOT NULL, " +
                MovieFavoriteEntry.COLUMN_POPULARITY + " FLOAT NOT NULL, " +
                MovieFavoriteEntry.COLUMN_VOTE_AVERAGE + " FLOAT NOT NULL, " +
                MovieFavoriteEntry.COLUMN_VOTE_COUNT + " STRING NOT NULL " +
                "); ";

        database.execSQL(SQL_CREATE_FAVORITE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + MovieFavoriteEntry.TABLE_NAME);
        onCreate(database);
    }
}