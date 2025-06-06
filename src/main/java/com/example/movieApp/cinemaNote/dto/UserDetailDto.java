package com.example.movieApp.cinemaNote.dto;

import com.example.movieApp.cinemaNote.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailDto {
    private String nickname;
    private String email;
    private String profilePic;
    private String phone;

    public static UserDetailDto from(Member member) {
        return new UserDetailDto(
                member.getUsername(),
                member.getEmail(),
                member.getProfileImg(),
                member.getPhone()
        );
    }
}