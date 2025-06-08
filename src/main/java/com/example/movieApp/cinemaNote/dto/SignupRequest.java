package com.example.movieApp.cinemaNote.dto;

import com.example.movieApp.cinemaNote.domain.Gender;
import com.example.movieApp.cinemaNote.domain.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignupRequest {
    private String email;
    private String password;
    private String username;
    private String phone;
    private Gender gender;
    private String profileImg;
    private Role role;

    @Builder
    public SignupRequest(String email, String password, String username, String phone, Gender gender, String profileImg) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.gender = gender;
        this.profileImg = profileImg;
    }
}