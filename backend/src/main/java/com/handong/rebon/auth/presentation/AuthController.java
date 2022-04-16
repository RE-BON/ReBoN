package com.handong.rebon.auth.presentation;

import com.handong.rebon.auth.application.AuthService;
import com.handong.rebon.auth.application.dto.request.LoginRequestDto;
import com.handong.rebon.auth.application.dto.response.LoginResponseDto;
import com.handong.rebon.auth.infrastructure.JwtUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth/{oauthProvider}/login/token")
    public ResponseEntity login(@PathVariable String oauthProvider, @RequestParam String code) {
        LoginResponseDto response = authService.login(new LoginRequestDto(oauthProvider, code));
        String bearerToken = JwtUtils.makeBearerType(response.getToken());

        return ResponseEntity.ok()
                             .header("Authorization", bearerToken)
                             .build();
    }
}
