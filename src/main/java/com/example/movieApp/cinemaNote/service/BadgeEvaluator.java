package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Badge;
import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.Post;
import com.example.movieApp.cinemaNote.repository.BadgeRepository;
import com.example.movieApp.cinemaNote.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BadgeEvaluator {

    private final BadgeRepository badgeRepository;
    private final PostRepository postRepository;

    public void evaluateBadges(Member member) {
        List<Post> posts = postRepository.findByMember(member);

        Map<String, Long> genreCount = posts.stream()
                .filter(p -> p.getMovie() != null && p.getMovie().getGenre() != null)
                .collect(Collectors.groupingBy(
                        p -> p.getMovie().getGenre(),
                        Collectors.counting()
                ));

        genreCount.forEach((genre, count) -> {
            if (count >= 5) {
                String badgeName = genre + "왕";  // 예: 감성왕
                boolean alreadyEarned = badgeRepository.findByMember(member).stream()
                        .anyMatch(b -> b.getName().equals(badgeName));
                if (!alreadyEarned) {
                    Badge badge = Badge.builder()
                            .member(member)
                            .name(badgeName)
                            .build();
                    badgeRepository.save(badge);
                }
            }
        });
    }
}