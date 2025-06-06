package com.example.movieApp.cinemaNote.dto;

import com.example.movieApp.cinemaNote.domain.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private float rating;
    private String movieName;

    // 기존 방식 (태그 포함 시 사용)
    public static PostResponseDto from(Post post, List<String> tagNames) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .rating(post.getRating())
                .author(post.getMember().getUsername())
                .movieName(post.getMovie() != null ? post.getMovie().getTitle() : null)
                .build();
    }

    // 오버로딩된 기본 from 메서드 (메서드 참조용)
    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .rating(post.getRating())
                .author(post.getMember().getUsername())
                .movieName(post.getMovie() != null ? post.getMovie().getTitle() : null)
                .build();
    }
}