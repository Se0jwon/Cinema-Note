package com.example.movieApp.cinemaNote.dto;

import com.example.movieApp.cinemaNote.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SignupRequest {
    private String username;
    private String nickname;
    private String password;
    private String phone;
    private String email;
    private String profileImg;
    private Gender gender;
}


