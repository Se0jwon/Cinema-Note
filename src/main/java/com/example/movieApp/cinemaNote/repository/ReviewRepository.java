package com.example.movieApp.cinemaNote.repository;

import com.example.movieApp.cinemaNote.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(String movieId);
}