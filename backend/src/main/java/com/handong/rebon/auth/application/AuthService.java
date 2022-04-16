package com.handong.rebon.auth.application;

import com.handong.rebon.auth.application.dto.request.LoginRequestDto;
import com.handong.rebon.auth.application.dto.response.LoginResponseDto;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.JwtUtils;
import com.handong.rebon.auth.infrastructure.OauthHandler;
import com.handong.rebon.exception.oauth.NoSuchOAuthMemberException;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.repository.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final OauthHandler oauthHandler;
    private final JwtUtils jwtUtils;

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        OauthProvider oauthProvider = OauthProvider.ignoreCase(loginRequestDto.getOauthProvider());
        OauthUserInfo userInfoFromCode = oauthHandler.getUserInfoFromCode(oauthProvider, loginRequestDto.getCode());
        String email = userInfoFromCode.getEmail();

        Member member = memberRepository.findByEmail(email, oauthProvider)
                                        .orElseThrow(() -> new NoSuchOAuthMemberException(email));

        String token = jwtUtils.createToken(member.getId());

        LoginResponseDto response = new LoginResponseDto(token);

        return response;
    }
}
