package com.example.movieApp.cinemaNote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewRequestDto {
    @JsonProperty("reviewText")
    private String content;
    private float rating;
    private String movieId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}