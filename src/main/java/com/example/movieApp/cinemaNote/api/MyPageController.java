package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.dto.BadgeDto;
import com.example.movieApp.cinemaNote.dto.ProfileRequest;
import com.example.movieApp.cinemaNote.repository.MemberRepository;
import com.example.movieApp.cinemaNote.security.CustomUserDetails;
import com.example.movieApp.cinemaNote.dto.PostResponseDto;
import com.example.movieApp.cinemaNote.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> myPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(myPageService.getMyPosts(userDetails.getMember()));
    }

    @GetMapping("/badges")
    public ResponseEntity<List<BadgeDto>> myBadges(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(myPageService.getMyBadges(userDetails.getMember()));
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequest dto,
                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        Member member = memberRepository.findById(userDetails.getMember().getId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        member.setEmail(dto.getEmail());
        member.setUsername(dto.getUsername());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setPhone(dto.getPhone()); // ✅ 전화번호 업데이트
        member.setProfileImg(dto.getImageUrl());

        memberRepository.save(member);
        return ResponseEntity.ok().build();
    }
}