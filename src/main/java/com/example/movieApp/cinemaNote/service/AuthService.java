package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;

public interface AuthService {
    String login(LoginRequest request); // 로그인 성공 시 메시지 or 토큰
}