package com.handong.rebon.member.presentation.dto.response;

import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadResponse {
    private Long id;
    private String image;
    private String email;
    private String nickName;
    private boolean isAgreed;

    public static MemberReadResponse from(MemberCreateResponseDto memberCreateResponseDto) {
        return new MemberReadResponse(memberCreateResponseDto.getToken());
    }
}
