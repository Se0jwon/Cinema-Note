package com.example.movieApp.cinemaNote.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequestDto {

    private String title;     // 게시글 제목
    private String content;   // 게시글 내용
    private String movieTitle;

    @DecimalMin(value = "0.0", message = "별점은 0점 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "별점은 5점 이하여야 합니다.")
    private float rating;     // 사용자 별점 (0.5 단위, 최대 5.0)

    // 별점이 0.5 단위인지 검증하는 메서드
    @AssertTrue(message = "별점은 0.5 단위로만 입력 가능합니다.")
    public boolean isValidRatingStep() {
        return (rating * 10) % 5 == 0;
    }
}