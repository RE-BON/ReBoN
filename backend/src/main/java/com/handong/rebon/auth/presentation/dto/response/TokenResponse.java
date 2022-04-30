package com.handong.rebon.auth.presentation.dto.response;

import com.handong.rebon.auth.application.dto.response.LoginResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private String token;

    public static TokenResponse from(LoginResponseDto loginResponseDto) {
        return new TokenResponse(loginResponseDto.getToken());
    }
}
