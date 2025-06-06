package com.example.movieApp.cinemaNote.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProfileRequest {
    private String imageUrl;   // 프로필 이미지 (URL or Base64 등)
    private String username;   // 닉네임 (수정 가능하도록 추가)
    private String password;   // 비밀번호 변경 시 입력 (선택 사항)
}
