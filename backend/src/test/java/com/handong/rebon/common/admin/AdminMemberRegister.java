package com.handong.rebon.common.admin;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;
import com.handong.rebon.member.domain.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class AdminMemberRegister {
    @Autowired
    private MemberRepository memberRepository;

    public Member simpleRegister(String nickname, String email, String ouathProvider) {
        Member member = Member.builder()
                              .profile(new Profile(email, nickname))
                              .oauthProvider(ouathProvider)
                              .isAgreed(true)
                              .build();
        return memberRepository.save(member);
    }
}
