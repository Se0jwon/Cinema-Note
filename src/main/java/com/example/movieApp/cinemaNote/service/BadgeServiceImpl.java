package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.dto.BadgeDto;
import com.example.movieApp.cinemaNote.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;
    private final BadgeEvaluator badgeEvaluator;

    @Override
    public List<BadgeDto> evaluateAndAssignBadges(Member member) {
        badgeEvaluator.evaluateBadges(member); // 내부적으로 DB 저장까지 한다고 가정
        // Log evaluated badges
        if (badgeRepository.findByMember(member).isEmpty()) {
            System.out.println("No badges assigned to member ID: " + member.getId());
        }
        System.out.println("Evaluated badges for member ID: " + member.getId());
        return badgeRepository.findByMember(member)
                .stream()
                .map(BadgeDto::from)
                .collect(Collectors.toList());
    }
}