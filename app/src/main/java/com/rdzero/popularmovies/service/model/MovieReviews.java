package com.rdzero.popularmovies.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Generated("com.robohorse.robopojogenerator")
public class MovieReviews implements Parcelable {

	@SerializedName("author")
	private String author;

	@SerializedName("id")
	private String id;

	@SerializedName("content")
	private String content;

	@SerializedName("url")
	private String url;

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"MovieReviews{" +
			"author = '" + author + '\'' + 
			",id = '" + id + '\'' + 
			",content = '" + content + '\'' + 
			",url = '" + url + '\'' + 
			"}";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int i) {
		dest.writeString(author);
		dest.writeString(id);
		dest.writeString(content);
		dest.writeString(url);
	}

	private MovieReviews(Parcel in) {
		this.author = in.readString();
		this.id = in.readString();
		this.content = in.readString();
		this.url = in.readString();
	}

	public static final Parcelable.Creator<MovieReviews>
			CREATOR = new Parcelable.Creator<MovieReviews>() {
		@Override
		public MovieReviews createFromParcel(Parcel in) {
			return new MovieReviews(in);
		}

		@Override
		public MovieReviews[] newArray(int size) {
			return new MovieReviews[size];
		}
	};
}