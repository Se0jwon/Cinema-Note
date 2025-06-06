package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.dto.BadgeDto;
import com.example.movieApp.cinemaNote.dto.PostResponseDto;
import com.example.movieApp.cinemaNote.dto.UserDetailDto;
import com.example.movieApp.cinemaNote.repository.MemberRepository;
import com.example.movieApp.cinemaNote.repository.UserBadgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.movieApp.cinemaNote.repository.PostRepository; // import도 함께


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class MyPageServiceImpl implements MyPageService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final BadgeService badgeService;
    private final PostRepository postRepository;
    private final UserBadgeRepository userBadgeRepository;

    @Override
    public String profileEdit(String url, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));
        member.setProfileImg(url);
        return url;
    }

    @Override
    public Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));
    }

    @Override
    public List<PostResponseDto> getMyPosts(Member member) {
        return postRepository.findByMember(member).stream()
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
    }


    @Override
    public void evaluateBadges(Member member) {
        // 조건 만족하면 뱃지 생성 후 userBadgeRepository.save(...) 등 수행
    }

    @Override
    public List<BadgeDto> getMyBadges(Member member) {
        return userBadgeRepository.findByMember(member).stream()
                .map(userBadge -> BadgeDto.from(userBadge.getBadge())) // 'true'는 이미 획득한 뱃지라는 뜻
                .collect(Collectors.toList());
    }


    @Override
    public UserDetailDto findMemberDto(String username) {
        return memberRepository.findByUsername(username)
                .map(member -> new UserDetailDto(
                        member.getUsername(),
                        member.getEmail(),
                        member.getProfileImg(),
                        member.getPhone()
                ))
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    @Override
    public void editPassword(Member member, String oldPassword, String newPassword) {
        if (passwordEncoder.matches(oldPassword, member.getPassword())) {
            member.setPassword(passwordEncoder.encode(newPassword));
            memberRepository.save(member);
        } else {
            throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
        }
    }
}