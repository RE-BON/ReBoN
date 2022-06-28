package com.handong.rebon.auth.application;

import com.handong.rebon.auth.application.dto.request.LoginRequestDto;
import com.handong.rebon.auth.application.dto.response.LoginResponseDto;
import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.JwtProvider;
import com.handong.rebon.auth.infrastructure.OauthHandler;
import com.handong.rebon.exception.oauth.NoSuchOAuthMemberException;
import com.handong.rebon.member.application.MemberService;
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
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String oauthProvider = loginRequestDto.getOauthProvider();
        OauthUserInfo userInfo = oauthHandler.getUserInfoFromCode(oauthProvider, loginRequestDto.getCode());
        String email = userInfo.getEmail();

        Member member = memberRepository.findByProfileEmailAndOauthProvider(email, oauthProvider)
                                        .orElseThrow(() -> new NoSuchOAuthMemberException(email));

        String token = jwtProvider.createToken(String.valueOf(member.getId()));
        LoginResponseDto response = new LoginResponseDto(token);

        return response;
    }

    @Transactional(readOnly = true)
    public LoginMember findMemberByToken(String token) {
        if (!jwtProvider.isValidToken(token)) {
            return LoginMember.anonymous();
        }

        String payLoad = jwtProvider.getPayLoad(token);
        Long id = Long.parseLong(payLoad);
        Member member = memberService.findById(id);
        return new LoginMember(member.getId());
    }
}
