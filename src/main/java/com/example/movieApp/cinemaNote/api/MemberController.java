package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;
import com.example.movieApp.cinemaNote.security.CustomUserDetails;
import com.example.movieApp.cinemaNote.dto.ProfileRequest;
import com.example.movieApp.cinemaNote.dto.SignupRequest;
import com.example.movieApp.cinemaNote.dto.UserDetailDto;
import com.example.movieApp.cinemaNote.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest requestDto) {
        memberService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공");
    }
}