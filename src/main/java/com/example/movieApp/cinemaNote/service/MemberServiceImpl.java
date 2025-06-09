package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Gender;
import com.example.movieApp.cinemaNote.dto.ProfileRequest;
import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;
import com.example.movieApp.cinemaNote.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.Role;
import com.example.movieApp.cinemaNote.dto.SignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signup(SignupRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .phone(request.getPhone())
                .profileImg(request.getProfileImg())
                .role(request.getRole())
                .gender(request.getGender())
                .build();

        memberRepository.save(member);
    }

    @Override
    public boolean login(LoginRequest request) {
        return memberRepository.findByEmail(request.getEmail())
                .filter(member -> passwordEncoder.matches(request.getPassword(), member.getPassword()))
                .isPresent();
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public void updateProfile(Member member, ProfileRequest request) {
        member.setUsername(request.getUsername());
        member.setProfileImg(request.getImageUrl());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            member.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }
}