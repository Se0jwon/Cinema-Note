package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.dto.ProfileRequest;
import com.example.movieApp.cinemaNote.dto.SignupRequest;
import com.example.movieApp.cinemaNote.domain.Member;

public interface MemberService {



    // 회원가입 요청 처리
    void signup(SignupRequest request);

    // 프로필(닉네임, 이미지 등) 업데이트
    void updateProfile(Member member, ProfileRequest request);

    // 이메일 기준 사용자 조회 (일반 로그인, 소셜 로그인 등에서 활용)
    Member findByEmail(String email);

    // 이메일 존재 여부 확인 (중복 방지용)
    boolean existsByEmail(String email);
}