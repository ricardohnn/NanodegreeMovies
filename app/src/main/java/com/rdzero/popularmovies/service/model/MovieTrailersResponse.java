package com.rdzero.popularmovies.service.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class MovieTrailersResponse {

	@SerializedName("id")
	private int id;

	@SerializedName("results")
	private List<MovieTrailers> results;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setResults(List<MovieTrailers> results){
		this.results = results;
	}

	public List<MovieTrailers> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"MovieTrailersResponse{" +
			"id = '" + id + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}