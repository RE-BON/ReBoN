package com.handong.rebon.member.presentation.dto;

import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.application.dto.request.MemberUpdateRequestDto;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;
import com.handong.rebon.member.presentation.dto.request.MemberUpdateRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberAssembler {
    public static MemberCreateRequestDto toMemberCreateDto(MemberCreateRequest memberCreateRequest) {
        return new MemberCreateRequestDto(
                memberCreateRequest.getEmail(),
                memberCreateRequest.getOauthProvider(),
                memberCreateRequest.getNickname(),
                memberCreateRequest.isAgreed());
    }

    public static MemberUpdateRequestDto memberUpdateRequestDto(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        return MemberUpdateRequestDto.builder()
                                     .memberId(memberId)
                                     .nickname(memberUpdateRequest.getNickname())
                                     .agreed(memberUpdateRequest.isAgreed())
                                     .build();
    }
}
