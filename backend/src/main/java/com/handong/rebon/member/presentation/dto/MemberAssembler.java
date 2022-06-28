package com.handong.rebon.member.presentation.dto;

import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;

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
}
