package com.handong.rebon.member.application;

import com.handong.rebon.exception.member.MemberNotFoundException;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.repository.MemberRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //Review 메서드가 잘 돌아가는지 확인을 위해 임시로 만든 메서드
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }
}
