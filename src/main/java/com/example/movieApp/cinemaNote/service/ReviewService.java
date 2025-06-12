package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.dto.ReviewRequestDto;
import com.example.movieApp.cinemaNote.dto.ReviewResponseDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    void saveReview(ReviewRequestDto dto, String username);
    List<ReviewResponseDto> getReviewsByMovieId(String movieId);
}