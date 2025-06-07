package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;
import com.example.movieApp.cinemaNote.security.JwtToken;

public interface AuthService {
    JwtToken login(LoginRequest request); // 로그인 성공 시 access & refresh 토큰 반환
}