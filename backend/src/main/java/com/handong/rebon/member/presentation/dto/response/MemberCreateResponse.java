package com.handong.rebon.member.presentation.dto.response;

import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateResponse {
    private String token;

    public static MemberCreateResponse from(MemberCreateResponseDto memberCreateResponseDto) {
        return new MemberCreateResponse(memberCreateResponseDto.getToken());
    }
}
