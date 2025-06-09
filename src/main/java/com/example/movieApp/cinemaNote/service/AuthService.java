package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;
import com.example.movieApp.cinemaNote.security.JwtToken;

// AuthService.java
public interface AuthService {
    JwtToken login(LoginRequest request);

    String findUserName(String email); // ✅ 이거 추가
}