package com.handong.rebon.auth.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private String oauthProvider;
    private String code;
}
