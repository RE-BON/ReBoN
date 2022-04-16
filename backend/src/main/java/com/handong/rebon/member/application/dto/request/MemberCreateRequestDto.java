package com.handong.rebon.member.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateRequestDto {
    private String email;
    private String oauthProvider;
    private String nickname;
    private boolean agreed;
}
