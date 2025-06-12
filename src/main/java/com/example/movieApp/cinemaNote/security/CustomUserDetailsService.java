package com.example.movieApp.cinemaNote.security;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // CustomUserDetailsService.java
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username) // 여기서 email로 찾고 있음
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new CustomUserDetails(member);
    }
}