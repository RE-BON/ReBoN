package com.handong.rebon.member.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberReadResponseDto {
    private Long id;
    private String image;
    private String email;
    private String nickName;
    private boolean isAgreed;
}