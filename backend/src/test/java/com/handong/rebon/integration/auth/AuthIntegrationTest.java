package com.handong.rebon.integration.auth;

import com.handong.rebon.auth.application.AuthService;
import com.handong.rebon.auth.application.dto.request.LoginRequestDto;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.JwtProvider;
import com.handong.rebon.auth.infrastructure.OauthHandler;
import com.handong.rebon.exception.oauth.NoSuchOAuthMemberException;
import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.domain.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AuthIntegrationTest extends IntegrationTest {

    @MockBean
    OauthHandler oauthHandler;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    AuthService authService;

    @MockBean
    JwtProvider jwtProvider;

    @Test
    @DisplayName("동일한 이메일, 다른 제공자일 경우 exception이 발생한다.")
    void loginSameEmailInvalidOauthProviderException() {
        //given
        String code = "test-code";
        String email = "test@gmail.com";
        String registeredProvider = "google";
        String invalidProvider = "naver";
        OauthUserInfo mockOauthUserInfo = mock(OauthUserInfo.class);

        given(oauthHandler.getUserInfoFromCode(invalidProvider, code))
                .willReturn(mockOauthUserInfo);
        given(mockOauthUserInfo.getEmail())
                .willReturn(email);
        memberService.save(new MemberCreateRequestDto(email, registeredProvider, "test", true));

        //when,then
        Assertions.assertThatThrownBy(() -> authService.login(new LoginRequestDto("naver", code)))
                  .isInstanceOf(NoSuchOAuthMemberException.class);
    }

}
