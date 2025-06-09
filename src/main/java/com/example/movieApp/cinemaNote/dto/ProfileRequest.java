package com.example.movieApp.cinemaNote.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProfileRequest {
    private String email;
    private String imageUrl;   // 프로필 이미지
    private String username;   // 닉네임
    private String password;   // 비밀번호
    private String phone;      // ✅ 전화번호 추가
}
