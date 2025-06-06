package com.example.movieApp.cinemaNote.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchRequest {
    private String keyword;         // 제목/내용 검색용 키워드
    private Long tagId;             // 선택된 태그 ID
    private LocalDateTime from;
    private LocalDateTime to;
}
