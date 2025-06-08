package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Movie;
import com.example.movieApp.cinemaNote.dto.MovieDto;
import com.example.movieApp.cinemaNote.repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    @Value("${tmdb.api-key}")
    private String tmdbApiKey;

    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Movie searchAndSaveMovie(String title) {
        // Step 1: TMDB 검색
        String url = "https://api.themoviedb.org/3/search/movie?query=" + URLEncoder.encode(title, UTF_8) + "&api_key=" + tmdbApiKey;
        JsonNode results = restTemplate.getForEntity(url, JsonNode.class).getBody().get("results");
        if (results == null || results.size() == 0) return null;

        // Step 2: 정확히 일치하는 영화 찾기
        JsonNode matched = null;
        for (JsonNode node : results) {
            if (title.equalsIgnoreCase(node.get("title").asText())) {
                matched = node;
                break;
            }
        }

        if (matched == null) matched = results.get(0); // fallback

        Long tmdbId = matched.get("id").asLong();

        // Step 3: 중복 확인
        return movieRepository.findByTmdbId(tmdbId).orElseGet(() -> {
            // 상세 요청
            Movie movie = new Movie();
            return movieRepository.save(movie);
        });
    }

    public List<MovieDto> searchByGenres(List<Integer> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            throw new IllegalArgumentException("장르 ID 리스트가 비어있습니다.");
        }
        String genreParam = genreIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String discoverUrl = "https://api.themoviedb.org/3/discover/movie?with_genres=" + genreParam + "&api_key=" + tmdbApiKey;

        ResponseEntity<JsonNode> discoverResponse = restTemplate.getForEntity(discoverUrl, JsonNode.class);
        JsonNode results = discoverResponse.getBody().get("results");
        if (results == null || results.size() == 0) {
            return new ArrayList<>();
        }

        List<MovieDto> movies = new ArrayList<>();
        for (JsonNode result : results) {
            Long tmdbId = result.get("id").asLong();

            // Check if movie already exists in repository
            Movie movie = movieRepository.findByTmdbId(tmdbId).orElse(null);
            if (movie == null) {
                // Fetch detailed info
                String detailUrl = "https://api.themoviedb.org/3/movie/" + tmdbId + "?api_key=" + tmdbApiKey;
                ResponseEntity<JsonNode> detailResponse = restTemplate.getForEntity(detailUrl, JsonNode.class);
                JsonNode movieJson = detailResponse.getBody();

                String movieTitle = movieJson.get("title").asText();
                String posterPath = movieJson.hasNonNull("poster_path") ? movieJson.get("poster_path").asText() : null;
                String genre = null;
                if (movieJson.has("genres") && movieJson.get("genres").size() > 0) {
                    genre = movieJson.get("genres").get(0).get("name").asText();
                }

                movie = new Movie();
                movie.setTmdbId(tmdbId);
                movie.setTitle(movieTitle);
                movie.setPosterPath(posterPath);
                movie.setGenre(genre);

                movie = movieRepository.save(movie);
            }
            movies.add(new MovieDto(
                    movie.getTmdbId(),
                    movie.getTitle(),
                    movie.getPosterPath(),
                    movie.getGenre()
            ));
        }
        return movies;
    }

    @Override
    public String searchMovie(String query) {
        // TMDB API 호출해서 JSON 결과 통째로 리턴하는 예시
        String url = "https://api.themoviedb.org/3/search/movie?query=" +
                URLEncoder.encode(query, UTF_8) + "&api_key=" + tmdbApiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}