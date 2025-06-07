package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;
import com.example.movieApp.cinemaNote.security.JwtToken;
import com.example.movieApp.cinemaNote.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<java.util.Map<String, Object>> login(@RequestBody LoginRequest request) {
        JwtToken token = authService.login(request);

        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", "로그인 성공");

        java.util.Map<String, String> data = new java.util.HashMap<>();
        data.put("accessToken", token.getAccessToken());
        data.put("refreshToken", token.getRefreshToken());

        response.put("data", data);

        return ResponseEntity.ok(response);
    }
}