package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.Movie;
import com.example.movieApp.cinemaNote.domain.Post;
import com.example.movieApp.cinemaNote.dto.PostRequestDto;
import com.example.movieApp.cinemaNote.dto.PostResponseDto;
import com.example.movieApp.cinemaNote.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final BadgeEvaluator badgeEvaluator;
    private final MovieService movieService;


    @Override
    public List<PostResponseDto> searchPosts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            // 키워드가 없을 경우 전체 게시글 반환
            return getAllPosts();
        }

        return postRepository.findAll().stream()
                .filter(post ->
                        (post.getTitle() != null && post.getTitle().toLowerCase().contains(keyword.toLowerCase())) ||
                                (post.getContent() != null && post.getContent().toLowerCase().contains(keyword.toLowerCase()))
                )
                .map(PostResponseDto::from)
                .collect(Collectors.toList());


    }

    @Override
    public String createPost(PostRequestDto dto, Member member) {
        // 1. 영화 제목으로 TMDB에서 Movie 가져오기
        Movie movie = movieService.searchAndSaveMovie(dto.getMovieTitle());

        // 2. 기존 게시글 존재 여부 확인 (같은 영화, 같은 멤버)
        Post existingPost = postRepository.findAll().stream()
                .filter(p -> p.getMovie() != null && p.getMovie().getId().equals(movie.getId())
                        && p.getMember() != null && p.getMember().getId().equals(member.getId()))
                .findFirst()
                .orElse(null);

        if (existingPost != null) {
            // 기존 게시글이 있으면 수정
            existingPost.setTitle(dto.getTitle());
            existingPost.setContent(dto.getContent());
            existingPost.setRating(dto.getRating());
            // 저장은 JPA 영속성 컨텍스트에 의해 자동 반영됨
            badgeEvaluator.evaluateBadges(member);
            return "수정되었습니다";
        } else {
            // 새 게시글 생성
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .member(member)
                    .rating(dto.getRating())
                    .movie(movie)
                    .build();
            postRepository.save(post);
            badgeEvaluator.evaluateBadges(member);
            return "작성되었습니다";
        }
    }

    @Override
    public void updatePost(Post post, PostRequestDto requestDto, Member member) {
        validateAuth(post, member);

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        post.setRating(requestDto.getRating());
    }

    @Override
    public void deletePost(Long postId, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다: " + postId));
        validateAuth(post, member);
        postRepository.delete(post);
    }

    @Override
    public void validateAuth(Post post, Member member) {
        if (!post.getMember().getId().equals(member.getId())) {
            throw new AccessDeniedException("작성자만 수정/삭제할 수 있습니다.");
        }
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다: " + postId));
        return PostResponseDto.from(post);
    }

    @Override
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> getPostsByDateRange(LocalDateTime from, LocalDateTime to) {
        return postRepository.findAll().stream()
                .filter(post -> post.getCreatedAt() != null &&
                        (post.getCreatedAt().isEqual(from) || post.getCreatedAt().isAfter(from)) &&
                        (post.getCreatedAt().isEqual(to) || post.getCreatedAt().isBefore(to)))
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
    }
}