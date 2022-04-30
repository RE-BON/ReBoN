package com.handong.rebon.member.presentation.dto.response;

import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateResponse {
    private String token;

    public static MemberCreateResponse from(MemberCreateResponseDto memberCreateResponseDto) {
        return new MemberCreateResponse(memberCreateResponseDto.getToken());
    }
}
