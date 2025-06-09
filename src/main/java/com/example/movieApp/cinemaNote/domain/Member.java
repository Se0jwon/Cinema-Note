package com.example.movieApp.cinemaNote.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name = "username")
    private String username;

    private String phone;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String email, String password, String username, String phone,
                  String profileImg, Gender gender, Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.profileImg = profileImg;
        this.gender = gender;
        this.role = role;
    }

}