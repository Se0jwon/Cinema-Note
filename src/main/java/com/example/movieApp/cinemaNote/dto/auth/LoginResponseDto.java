package com.example.movieApp.cinemaNote.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private boolean success;
    private String message;
    private DataField data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataField {
        private String accessToken;
        private String refreshToken;
    }
}