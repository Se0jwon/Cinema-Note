package com.example.movieApp.cinemaNote.service;

import com.example.movieApp.cinemaNote.domain.Member;
import com.example.movieApp.cinemaNote.dto.BadgeDto;
import com.example.movieApp.cinemaNote.dto.PostResponseDto;
import com.example.movieApp.cinemaNote.dto.UserDetailDto;

import java.util.List;

public interface MyPageService {

    String profileEdit(String url, String username);

    Member findMemberByUsername(String username);

    UserDetailDto findMemberDto(String username);

    void editPassword(Member member, String oldPassword, String newPassword);

    List<BadgeDto> getMyBadges(Member member);

    List<PostResponseDto> getMyPosts(Member member);

    void evaluateBadges(Member member);
}