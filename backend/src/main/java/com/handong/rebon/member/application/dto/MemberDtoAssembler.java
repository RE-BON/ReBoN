package com.handong.rebon.member.application.dto;

import com.handong.rebon.auth.domain.Login;
import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.member.application.dto.response.MemberReadResponseDto;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByMemberResponseDto;
import com.handong.rebon.review.domain.Review;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDtoAssembler {
    public static MemberReadResponseDto memberReadResponseDto(Member member) {
        return MemberReadResponseDto.builder()
                                    .id(member.getId())
                                    .image(member.getImage())
                                    .email(member.getEmail())
                                    .nickName(member.getNickName())
                                    .isAgreed(member.isAgreed())
                                    .build();
    }


    //    public static List<MemberReadResponseDto> memberReadResponseDtos(@Login LoginMember loginMember) {
    //        Long id = loginMember.getId();

    // 여기부터 시작. loginMember로 id를 구하는게 맞는지, 그리고 이 id로부터 member의 정보를 어떻게 받아와야 할지. findById??
    //        return new MemberReadResponseDto(loginMember.getId(), loginMember.getProfile(), loginMember.getNickName())
    //                   .collect(Collectors.toList());
}