package com.example.movieApp.cinemaNote.repository;

import com.example.movieApp.cinemaNote.domain.Badge;
import com.example.movieApp.cinemaNote.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<Badge> findByMember(Member member);
}