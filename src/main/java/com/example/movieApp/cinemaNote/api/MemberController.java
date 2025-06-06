package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.security.CustomUserDetails;
import com.example.movieApp.cinemaNote.dto.ProfileRequest;
import com.example.movieApp.cinemaNote.dto.SignupRequest;
import com.example.movieApp.cinemaNote.dto.UserDetailDto;
import com.example.movieApp.cinemaNote.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        memberService.signup(request);
        return ResponseEntity.ok("회원가입 완료!");
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailDto> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(UserDetailDto.from(userDetails.getMember()));
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileRequest dto,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        memberService.updateProfile(userDetails.getMember(), dto);
        return ResponseEntity.ok().build();
    }
}