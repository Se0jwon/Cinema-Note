package com.example.movieApp.cinemaNote.repository;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
    List<UserBadge> findByMember(Member member);
}