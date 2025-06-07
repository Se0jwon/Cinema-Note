package com.example.movieApp.cinemaNote.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
    private String grantType; // 예: "Bearer"
    private String accessToken;
    private String refreshToken; // 추가됨
}