package com.example.movieApp.cinemaNote.domain;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TMDB에서 받아온 영화 고유 ID (문자열인 경우도 있어요)
    @Column(nullable = false, unique = true)
    private Long tmdbId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String overview; // 줄거리

    private String genre;

    private String posterPath; // 이미지 URL


    private LocalDate releaseDate;

}