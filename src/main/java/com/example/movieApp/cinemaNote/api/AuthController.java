package com.example.movieApp.cinemaNote.api;

import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;
import com.example.movieApp.cinemaNote.dto.auth.LoginResponseDto;
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
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequest request) {
        JwtToken token = authService.login(request);

        LoginResponseDto.DataField data = new LoginResponseDto.DataField();
        data.setAccessToken(token.getAccessToken());
        data.setRefreshToken(token.getRefreshToken());

        LoginResponseDto response = new LoginResponseDto(true, "로그인 성공", data);

        return ResponseEntity.ok(response);
    }
}