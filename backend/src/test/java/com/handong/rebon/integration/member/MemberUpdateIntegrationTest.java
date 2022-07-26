package com.handong.rebon.integration.member;

import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.application.dto.request.MemberUpdateRequestDto;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberUpdateIntegrationTest extends IntegrationTest {
    @Autowired
    MemberService memberService;

    @Autowired
    public MemberRepository memberRepository;

    @Test
    @DisplayName("내 정보를 수정할 수 있다.")
    void updateMyInfo() {

        //given
        Member member = new Member("test@test.com", "test", true, "google");
        Member createdMember = memberRepository.save(member);

        MemberUpdateRequestDto memberUpdateRequestDto = MemberUpdateRequestDto.builder()
                                                                              .memberId(createdMember.getId())
                                                                              .nickname("랄프")
                                                                              .agreed(false)
                                                                              .build();

        //when
        memberService.update(memberUpdateRequestDto);
        Member updatedMember = memberRepository.findById(member.getId()).get();

        //then
        assertThat(updatedMember)
                .extracting("nickName")
                .isEqualTo(memberUpdateRequestDto.getNickname());
    }
}
