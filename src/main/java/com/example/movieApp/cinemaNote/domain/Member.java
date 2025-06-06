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
    private String email; // 로그인 ID (이메일)

    private String password; // 일반 로그인용 (소셜 로그인은 null 가능)

    private String username; // 닉네임 (앱 내 표시 이름)

    private String phone;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String provider;   // 소셜 플랫폼 (google, kakao 등)
    private String providerId; // 소셜 플랫폼 사용자 ID

    @Enumerated(EnumType.STRING)
    private Role role;

    // 내가 작성한 리뷰 리스트
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();


    // 내가 받은 칭호들
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBadge> badges = new ArrayList<>();
}