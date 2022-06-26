package com.handong.rebon.integration.member;

import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.application.dto.response.MemberReadResponseDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberReadIntegrationTest extends IntegrationTest {
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("내 정보를 조회할 수 있다.")
    public void findMemberInfos() {
        //given
        String email = "test@gmail.com";
        String registeredProvider = "google";
        String nickName = "test";

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
