package com.rdzero.popularmovies.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class MoviesDetails implements Parcelable {

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("video")
    private boolean video;

    @SerializedName("title")
    private String title;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("id")
    private int id;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("vote_count")
    private int voteCount;

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isVideo() {
        return video;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public String toString() {
        return
                "MoviesDetails{" +
                        "overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",original_title = '" + originalTitle + '\'' +
                        ",video = '" + String.valueOf(video) + '\'' +
                        ",title = '" + title + '\'' +
                        ",genre_ids1 = '" + genreIds.get(0).toString() + '\'' +
                        ",poster_path = '" + posterPath.toString() + '\'' +
                        ",backdrop_path = '" + backdropPath.toString() + '\'' +
                        ",release_date = '" + releaseDate.toString() + '\'' +
                        ",vote_average = '" + String.valueOf(voteAverage) + '\'' +
                        ",popularity = '" + String.valueOf(popularity) + '\'' +
                        ",id = '" + String.valueOf(id) + '\'' +
                        ",adult = '" + String.valueOf(adult) + '\'' +
                        ",vote_count = '" + String.valueOf(voteCount) + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(overview);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeInt(video ? 1 : 0);
        dest.writeString(title);
        dest.writeList(genreIds);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeString(releaseDate);
        dest.writeFloat(voteAverage);
        dest.writeDouble(popularity);
        dest.writeInt(id);
        dest.writeInt(adult ? 1 : 0);
        dest.writeInt(voteCount);
    }

    private MoviesDetails(Parcel in) {
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.video = (in.readInt() == 0 ? false : true);
        this.title = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, null);
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readFloat();
        this.popularity = in.readDouble();
        this.id = in.readInt();
        this.adult = (in.readInt() == 0 ? false : true);
        this.voteCount = in.readInt();
    }

    public static final Parcelable.Creator<MoviesDetails>
            CREATOR = new Parcelable.Creator<MoviesDetails>() {
        @Override
        public MoviesDetails createFromParcel(Parcel in) {
            return new MoviesDetails(in);
        }

        @Override
        public MoviesDetails[] newArray(int size) {
            return new MoviesDetails[size];
        }
    };
}