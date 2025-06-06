package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.Post;
import com.example.movieApp.cinemaNote.dto.PostRequestDto;
import com.example.movieApp.cinemaNote.dto.PostResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PostService {
    String createPost(PostRequestDto post, Member member);

    void updatePost(Post post, PostRequestDto requestDto, Member member);

    void deletePost(Long postId, Member member); // 🔧 수정된 부분

    PostResponseDto getPostById(Long postId);

    List<PostResponseDto> getAllPosts();

    void validateAuth(Post post, Member member);

    List<PostResponseDto> getPostsByDateRange(LocalDateTime from, LocalDateTime to);

    List<PostResponseDto> searchPosts(String keyword);
}