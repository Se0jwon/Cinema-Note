package com.example.movieApp.cinemaNote.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewResponseDto {
    private String username;
    private String profileUrl;
    private String content;
    private float rating;
    private String createdDate;
}