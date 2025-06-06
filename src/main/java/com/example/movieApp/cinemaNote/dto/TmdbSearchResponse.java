package com.example.movieApp.cinemaNote.dto;


import lombok.Data;
import java.util.List;

@Data
public class TmdbSearchResponse {
    private int page;
    private List<MovieInfoDto> results;
    private int total_results;
    private int total_pages;
}
