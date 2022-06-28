package com.handong.rebon.auth.presentation;

import com.handong.rebon.auth.application.AuthService;
import com.handong.rebon.auth.application.dto.request.LoginRequestDto;
import com.handong.rebon.auth.application.dto.response.LoginResponseDto;
import com.handong.rebon.auth.presentation.dto.response.TokenResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth/{oauthProvider}/login/token")
    public ResponseEntity<TokenResponse> login(@PathVariable String oauthProvider, @RequestParam String code) {
        LoginResponseDto loginResponseDto = authService.login(new LoginRequestDto(oauthProvider, code));

        return ResponseEntity.ok(TokenResponse.from(loginResponseDto));

    }
}
