package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Movie;
import com.example.movieApp.cinemaNote.dto.MovieDto;

import java.util.List;

public interface MovieService {
    Movie searchAndSaveMovie(String title); // ← 지금 구현된 핵심 메서드
    List<MovieDto> searchByGenres(List<Integer> genreIds); // ← 장르 기반 검색 (원한다면)
    String searchMovie(String query);
}
