package com.example.movieApp.cinemaNote.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieId;

    private String content;

    private float rating;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}