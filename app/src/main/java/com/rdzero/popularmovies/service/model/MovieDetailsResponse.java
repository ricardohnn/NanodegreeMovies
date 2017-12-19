package com.rdzero.popularmovies.service.model;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

//TODO https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit

@Generated("com.robohorse.robopojogenerator")
public class MovieDetailsResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<MovieDetails> results;

    @SerializedName("total_results")
    private int totalResults;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setResults(List<MovieDetails> results) {
        this.results = results;
    }

    public List<MovieDetails> getResults() {
        return results;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return
                "MoviesListResponse{" +
                        "page = '" + page + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        ",total_results = '" + totalResults + '\'' +
                        "}";
    }
}