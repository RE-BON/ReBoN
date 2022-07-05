package com.handong.rebon.member.application.dto;

import com.handong.rebon.member.application.dto.response.MemberReadResponseDto;
import com.handong.rebon.member.domain.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}