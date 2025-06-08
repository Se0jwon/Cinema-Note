package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.dto.auth.LoginRequest;
import com.example.movieApp.cinemaNote.repository.MemberRepository;
import com.example.movieApp.cinemaNote.security.JwtToken;
import com.example.movieApp.cinemaNote.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtToken login(LoginRequest request) {
        try {
            // 이 부분에서 비밀번호 검증까지 Spring Security가 처리함
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            throw new RuntimeException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        // 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        // ✅ matches 중복 검증 제거: authenticationManager가 이미 처리함

        // JWT 토큰 생성
        String accessToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getEmail(), member.getRole().name());

        System.out.println("✅ 로그인 성공: " + member.getEmail());

        return new JwtToken("Bearer", accessToken, refreshToken);
    }
}