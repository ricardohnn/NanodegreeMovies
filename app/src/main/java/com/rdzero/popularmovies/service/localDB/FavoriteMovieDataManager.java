package com.rdzero.popularmovies.service.localDB;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.rdzero.popularmovies.service.localDB.FavoriteMoviesContract.MovieFavoriteEntry;

/**
 * Created by ricardo.nakayama on 12/19/2017.
 */

public class FavoriteMovieDataManager extends ContentProvider {

    public static final String AUTHORITY = "com.rdzero.popularmovies";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private FavoriteMoviesDBHelper dbHelper;
    private String[] allColumns = {
            MovieFavoriteEntry.COLUMN_ID,
            MovieFavoriteEntry.COLUMN_TITLE,
            MovieFavoriteEntry.COLUMN_ORIGINAL_TITLE,
            MovieFavoriteEntry.COLUMN_POSTER_PATH,
            MovieFavoriteEntry.COLUMN_OVERVIEW,
            MovieFavoriteEntry.COLUMN_RELEASE_DATE,
            MovieFavoriteEntry.COLUMN_POPULARITY,
            MovieFavoriteEntry.COLUMN_VOTE_AVERAGE,
            MovieFavoriteEntry.COLUMN_VOTE_COUNT,
            MovieFavoriteEntry.COLUMN_FAVORITE
    };

    @Override
    public boolean onCreate() {
        dbHelper = new FavoriteMoviesDBHelper(getContext());
        return true;
    }

    public static String getTableName(Uri uri){
        String value = uri.getPath();
        value = value.replace("/", "");//we need to remove '/'
        return value;
    }

    @Override
    public int delete(Uri uri, String where, String[] args) {
        String table = getTableName(uri);
        SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
        return dataBase.delete(table, where, args);
    }

    @Override
    public String getType(Uri arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        String table = getTableName(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long value = database.insert(table, null, initialValues);
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(value));
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String table = getTableName(uri);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return database.query(table, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String whereClause,
                      String[] whereArgs) {
        String table = getTableName(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.update(table, values, whereClause, whereArgs);
    }
}
