package com.rdzero.popularmovies.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Generated("com.robohorse.robopojogenerator")
public class MovieTrailers implements Parcelable {

	@SerializedName("site")
	private String site;

	@SerializedName("size")
	private int size;

	@SerializedName("iso_3166_1")
	private String iso31661;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("type")
	private String type;

	@SerializedName("iso_639_1")
	private String iso6391;

	@SerializedName("key")
	private String key;

	public void setSite(String site){
		this.site = site;
	}

	public String getSite(){
		return site;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setIso31661(String iso31661){
		this.iso31661 = iso31661;
	}

	public String getIso31661(){
		return iso31661;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setIso6391(String iso6391){
		this.iso6391 = iso6391;
	}

	public String getIso6391(){
		return iso6391;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	@Override
 	public String toString(){
		return 
			"MovieTrailers{" +
			"site = '" + site + '\'' + 
			",size = '" + size + '\'' + 
			",iso_3166_1 = '" + iso31661 + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			",key = '" + key + '\'' + 
			"}";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int i) {
		dest.writeString(site);
		dest.writeInt(size);
		dest.writeString(iso31661);
		dest.writeString(name);
		dest.writeString(id);
		dest.writeString(type);
		dest.writeString(iso6391);
		dest.writeString(key);

	}

	private MovieTrailers(Parcel in) {
		this.site = in.readString();
		this.size = in.readInt();
		this.iso31661 = in.readString();
		this.name = in.readString();
		this.id = in.readString();
		this.type = in.readString();
		this.iso6391 = in.readString();
		this.key = in.readString();
	}

	public static final Parcelable.Creator<MovieTrailers>
			CREATOR = new Parcelable.Creator<MovieTrailers>() {
		@Override
		public MovieTrailers createFromParcel(Parcel in) {
			return new MovieTrailers(in);
		}

		@Override
		public MovieTrailers[] newArray(int size) {
			return new MovieTrailers[size];
		}
	};
}