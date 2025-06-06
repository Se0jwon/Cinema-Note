package com.example.movieApp.cinemaNote.dto;

import com.example.movieApp.cinemaNote.domain.Badge;
import lombok.Getter;

@Getter
public class BadgeDto {
    private String name;

    public BadgeDto(String name) {
        this.name = name;
    }

    public static BadgeDto from(Badge badge) {
        return new BadgeDto(badge.getName()); // 예시: Badge에 getName()이 있다고 가정
    }
}