package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.Review;
import com.example.movieApp.cinemaNote.dto.ReviewRequestDto;
import com.example.movieApp.cinemaNote.dto.ReviewResponseDto;
import com.example.movieApp.cinemaNote.repository.MemberRepository;
import com.example.movieApp.cinemaNote.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public void saveReview(ReviewRequestDto dto, String username) {
        System.out.println("🧑 받은 사용자 이름: " + username);
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("회원 정보 없음"));

        System.out.println("✅ 회원 객체: " + member.getUsername());

        Review review = Review.builder()
                .movieId(dto.getMovieId())
                .content(dto.getContent())
                .rating(dto.getRating())
                .member(member)
                .createdDate(LocalDate.now())
                .build();

        reviewRepository.save(review);
        System.out.println("📝 리뷰 저장 완료");
    }

    @Override
    public List<ReviewResponseDto> getReviewsByMovieId(String movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);

        return reviews.stream().map(review -> ReviewResponseDto.builder()
                .username(review.getMember().getUsername())
                .profileUrl(review.getMember().getProfileImg())
                .content(review.getContent())
                .rating(review.getRating())
                .createdDate(review.getCreatedDate().toString())
                .build()
        ).collect(Collectors.toList());
    }
}