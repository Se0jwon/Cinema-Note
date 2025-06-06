package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Gender;
import com.example.movieApp.cinemaNote.dto.SignupRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataService {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        if (!memberService.existsByEmail("1234@example.com")) {
            memberService.signup(SignupRequest.builder()
                    .email("1234@example.com")
                    .password("1234")
                    .username("대건우")
                    .gender(Gender.MALE)
                    .phone("01073382156")
                    .profileImg("https://example.com/1.jpg")
                    .build());
        }

        if (!memberService.existsByEmail("5678@example.com")) {
            memberService.signup(SignupRequest.builder()
                    .email("5678@example.com")
                    .password("1234")
                    .username("소건우")
                    .gender(Gender.MALE)
                    .phone("01073382156")
                    .profileImg("https://example.com/2.jpg")
                    .build());
        }

        if (!memberService.existsByEmail("9012@example.com")) {
            memberService.signup(SignupRequest.builder()
                    .email("9012@example.com")
                    .password("1234")
                    .username("ㄱㄱㅇ")
                    .gender(Gender.MALE)
                    .phone("01073382156")
                    .profileImg("https://example.com/3.jpg")
                    .build());
        }
    }
}