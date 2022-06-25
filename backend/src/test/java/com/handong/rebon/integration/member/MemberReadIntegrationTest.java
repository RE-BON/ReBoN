package com.handong.rebon.integration.member;

import java.util.List;

import com.handong.rebon.auth.application.dto.request.LoginRequestDto;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.exception.oauth.NoSuchOAuthMemberException;
import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.application.dto.response.MemberReadResponseDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MemberReadIntegrationTest extends IntegrationTest {
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("내 정보를 조회할 수 있다.")
    public void findMemberInfos(){
        //given
        String code = "test-code";
        String email = "test@gmail.com";
        String registeredProvider = "google";
        String nickName ="test";
        OauthUserInfo mockOauthUserInfo = mock(OauthUserInfo.class);

        MemberCreateResponseDto memberCreateResponseDto = memberService.save(new MemberCreateRequestDto(email, registeredProvider, nickName, true));

        //when
        MemberReadResponseDto member = memberService.findByMemberId(memberCreateResponseDto.getMemberId());

        //then
        assertThat(member).extracting("email")
                          .isEqualTo(email);
        assertThat(member).extracting("nickName")
                          .isEqualTo(nickName);
        assertThat(member).extracting("isAgreed")
                          .isEqualTo(true);
    }
}
