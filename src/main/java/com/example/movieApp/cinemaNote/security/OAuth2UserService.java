package com.example.movieApp.cinemaNote.security;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.domain.Role;
import com.example.movieApp.cinemaNote.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) {
        OAuth2User oAuth2User = super.loadUser(request);
        String provider = request.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("id").toString();

        String email;
        if (provider.equals("google")) {
            email = oAuth2User.getAttribute("email");
        } else if (provider.equals("naver")) {
            Map<String, Object> response = oAuth2User.getAttribute("response");
            email = response.get("email").toString();
        } else if (provider.equals("kakao")) {
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            email = kakaoAccount.get("email").toString();
        } else {
            throw new RuntimeException("지원하지 않는 소셜 로그인입니다: " + provider);
        }

        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        Member member = memberOptional.orElseGet(() -> {
            return memberRepository.save(Member.builder()
                    .email(email)
                    .password("social") // 더 안전한 처리 가능
                    .username(provider + "_user")
                    .role(Role.USER)
                    .provider(provider)
                    .providerId(providerId)
                    .build());
        });

        return new CustomUserDetails(member);
    }
}
