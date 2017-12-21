package com.rdzero.popularmovies.service.localDB;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.rdzero.popularmovies.service.localDB.FavoriteMoviesContract.MovieFavoriteEntry;
import com.rdzero.popularmovies.service.model.MovieDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo.nakayama on 12/19/2017.
 */

public class FavoriteMovieDataManager extends ContentProvider {

    public static final String AUTHORITY = "com.rdzero.popularmovies";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private FavoriteMoviesDBHelper dbHelper;
    private static String[] allColumns = {
            MovieFavoriteEntry.COLUMN_ID,
            MovieFavoriteEntry.COLUMN_TITLE,
            MovieFavoriteEntry.COLUMN_ORIGINAL_TITLE,
            MovieFavoriteEntry.COLUMN_POSTER_PATH,
            MovieFavoriteEntry.COLUMN_BACKDROP_PATH,
            MovieFavoriteEntry.COLUMN_OVERVIEW,
            MovieFavoriteEntry.COLUMN_RELEASE_DATE,
            MovieFavoriteEntry.COLUMN_POPULARITY,
            MovieFavoriteEntry.COLUMN_VOTE_AVERAGE,
            MovieFavoriteEntry.COLUMN_VOTE_COUNT
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
        long value;
        try {
            value = database.insertOrThrow(table, null, initialValues);
            return Uri.withAppendedPath(CONTENT_URI, String.valueOf(value));
        } catch (SQLiteConstraintException ex){
            Log.d("com.rdzero.popularmovie", "NOT UNIQUE, inform that it is already added to the db.");
        }
        return null;
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

    public static Uri insertFavoriteMovie (MovieDetails movieDetails, Context context){
        ContentValues initialValues = new ContentValues();
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_ID, movieDetails.getId());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_TITLE, movieDetails.getTitle());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_ORIGINAL_TITLE, movieDetails.getOriginalTitle());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_POSTER_PATH, movieDetails.getPosterPath());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_BACKDROP_PATH, movieDetails.getBackdropPath());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_OVERVIEW, movieDetails.getOverview());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_RELEASE_DATE, movieDetails.getReleaseDate());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_POPULARITY, movieDetails.getPopularity());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_VOTE_AVERAGE, movieDetails.getVoteAverage());
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_VOTE_COUNT, movieDetails.getVoteCount());

        Uri contentUri = Uri.withAppendedPath(FavoriteMovieDataManager.CONTENT_URI, FavoriteMoviesContract.MovieFavoriteEntry.TABLE_NAME);
        Uri returnUri = context.getContentResolver().insert(contentUri, initialValues);
        if (returnUri != null){
            Toast.makeText(context, "Added to Favorite", Toast.LENGTH_SHORT).show();
            return returnUri;
        } else {
            removeFavoriteMovie(movieDetails.getId(), context);
            Toast.makeText(context, "Removed Favorite", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static int removeFavoriteMovie (int movieDetailsId, Context context){
        ContentValues initialValues = new ContentValues();
        initialValues.put(FavoriteMoviesContract.MovieFavoriteEntry.COLUMN_ID, movieDetailsId);

        Uri contentUri = Uri.withAppendedPath(FavoriteMovieDataManager.CONTENT_URI, FavoriteMoviesContract.MovieFavoriteEntry.TABLE_NAME);
        return context.getContentResolver().delete(contentUri, MovieFavoriteEntry.COLUMN_ID + " = " + movieDetailsId, null);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static List<MovieDetails> getFavoriteMoviesList(Context context){
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        Uri contentUri = Uri.withAppendedPath(FavoriteMovieDataManager.CONTENT_URI, FavoriteMoviesContract.MovieFavoriteEntry.TABLE_NAME);
        Cursor cursor = context.getContentResolver().query(
                contentUri,
                allColumns,
                null,
                null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            MovieDetails movieDetails = cursorToMovieDetails(cursor);
            movieDetailsList.add(movieDetails);
            cursor.moveToNext();
        }
        cursor.close();
        return movieDetailsList;
    }

    private static MovieDetails cursorToMovieDetails(Cursor cursor) {
        MovieDetails movieDetails = new MovieDetails();

        movieDetails.setId(cursor.getInt(0));
        movieDetails.setTitle(cursor.getString(1));
        movieDetails.setOriginalTitle(cursor.getString(2));
        movieDetails.setPosterPath(cursor.getString(3));
        movieDetails.setBackdropPath(cursor.getString(4));
        movieDetails.setOverview(cursor.getString(5));
        movieDetails.setReleaseDate(cursor.getString(6));
        movieDetails.setPopularity(cursor.getDouble(7));
        movieDetails.setVoteAverage(cursor.getFloat(8));
        movieDetails.setVoteCount(cursor.getInt(9));
        movieDetails.setOriginalLanguage("");
        movieDetails.setVideo(false);
        movieDetails.setGenreIds(null);
        movieDetails.setAdult(false);

        return movieDetails;
    }
}