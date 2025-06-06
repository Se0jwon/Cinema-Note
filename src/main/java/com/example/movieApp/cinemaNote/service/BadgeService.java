package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.dto.BadgeDto;

import java.util.List;

public interface BadgeService {
    List<BadgeDto> evaluateAndAssignBadges(Member member);
}