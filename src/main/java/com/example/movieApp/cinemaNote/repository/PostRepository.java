package com.example.movieApp.cinemaNote.repository;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 특정 유저가 특정 장르의 영화를 얼마나 많이 리뷰했는지 조회
    @Query("SELECT COUNT(p) FROM Post p WHERE p.member = :member AND p.movie.genre = :genre")
    long countByMemberAndMovieGenre(@Param("member") Member member, @Param("genre") String genre);

    // 특정 유저의 평균 별점
    @Query("SELECT AVG(p.rating) FROM Post p WHERE p.member = :member")
    float getAverageRatingByMember(@Param("member") Member member);

    // 유저가 작성한 전체 리뷰 수
    int countByMember(Member member);

    List<Post> findByMember(Member member);
}