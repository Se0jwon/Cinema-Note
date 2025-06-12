package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.dto.ReviewRequestDto;
import com.example.movieApp.cinemaNote.dto.ReviewResponseDto;
import com.example.movieApp.cinemaNote.security.CustomUserDetails;
import com.example.movieApp.cinemaNote.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 저장
    @PostMapping
    public ResponseEntity<Void> saveReview(@RequestBody ReviewRequestDto requestDto,
                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        System.out.println("💬 받은 리뷰 내용: " + requestDto.getContent());
        reviewService.saveReview(requestDto, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    // 특정 영화의 리뷰 전체 조회
    @GetMapping("/{movieId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviews(@PathVariable String movieId) {
        List<ReviewResponseDto> reviews = reviewService.getReviewsByMovieId(movieId);
        return ResponseEntity.ok(reviews);
    }
}
