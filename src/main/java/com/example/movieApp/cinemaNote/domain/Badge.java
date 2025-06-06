package com.example.movieApp.cinemaNote.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int requiredCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;  // 👈 이거 추가해야 .member()가 동작함
}