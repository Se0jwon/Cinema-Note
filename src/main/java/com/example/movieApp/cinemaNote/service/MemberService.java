package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.dto.ProfileRequest;
import com.example.movieApp.cinemaNote.dto.SignupRequest;
import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;

public interface MemberService {



    void signup(SignupRequest request);
    boolean login(LoginRequest request);
    Member findByEmail(String email);
    boolean existsByEmail(String email);
    void updateProfile(Member member, ProfileRequest request);
}