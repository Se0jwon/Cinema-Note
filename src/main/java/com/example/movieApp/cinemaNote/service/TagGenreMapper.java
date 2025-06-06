package com.example.movieApp.cinemaNote.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TagGenreMapper.java
@Component
public class TagGenreMapper {
    private static final Map<String, List<Integer>> tagToGenres = new HashMap<>();

    static {
        tagToGenres.put("상쾌한 아침", List.of(35, 10751));      // 코미디, 가족
        tagToGenres.put("잔잔한 시간", List.of(18));           // 드라마
        tagToGenres.put("눈물 폭발", List.of(18, 10749));     // 드라마, 로맨스
        tagToGenres.put("웃픈 코미디", List.of(35, 18));       // 코미디, 드라마
        // 여기에 추가
    }

    public List<Integer> getGenreIds(String tag) {
        return tagToGenres.getOrDefault(tag, List.of());
    }
}
