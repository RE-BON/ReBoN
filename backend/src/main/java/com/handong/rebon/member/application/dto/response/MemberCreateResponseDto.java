package com.handong.rebon.member.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateResponseDto {
    private String token;
    private Long memberId;
}
