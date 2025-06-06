package com.example.movieApp.cinemaNote.dto;

import com.example.movieApp.cinemaNote.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private Long tmdbId;
    private String title;
    private String genre;
    private String posterPath;
    private String overview;
    private LocalDate releaseDate;

    public MovieDto(Long tmdbId, String title, String posterPath, String genre) {
        this.id = null;
        this.tmdbId = tmdbId;
        this.title = title;
        this.posterPath = posterPath;
        this.genre = genre;
        this.overview = null;
        this.releaseDate = null;
    }

    public static MovieDto from(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTmdbId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getPosterPath(),
                movie.getOverview(),
                movie.getReleaseDate()
        );
    }
}