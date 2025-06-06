package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.dto.MovieDto;
import com.example.movieApp.cinemaNote.dto.MovieInfoDto;
import com.example.movieApp.cinemaNote.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class TmdbController {

    private final MovieService movieService;

    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam String query) {
        String result = movieService.searchMovie(query);
        return ResponseEntity.ok(result);
    }

    // 장르 기반 추천
    @GetMapping("/recommend")
    public ResponseEntity<List<MovieDto>> recommendByGenres(@RequestParam List<Integer> genres) {
        return ResponseEntity.ok(movieService.searchByGenres(genres));
    }
}