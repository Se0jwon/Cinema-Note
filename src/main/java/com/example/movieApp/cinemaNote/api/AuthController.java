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
        String username = authService.findUserName(request.getEmail());

        LoginResponseDto response = new LoginResponseDto(
                token.getAccessToken(),
                token.getRefreshToken(),
                username
        );

        return ResponseEntity.ok(response);
    }
}