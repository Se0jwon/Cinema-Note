package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.dto.BadgeDto;
import com.example.movieApp.cinemaNote.security.CustomUserDetails;
import com.example.movieApp.cinemaNote.dto.PostResponseDto;
import com.example.movieApp.cinemaNote.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> myPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(myPageService.getMyPosts(userDetails.getMember()));
    }

    @GetMapping("/badges")
    public ResponseEntity<List<BadgeDto>> myBadges(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(myPageService.getMyBadges(userDetails.getMember()));
    }
}