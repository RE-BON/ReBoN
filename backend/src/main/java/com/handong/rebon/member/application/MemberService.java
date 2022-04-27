package com.handong.rebon.member.application;

import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.infrastructure.JwtProvider;
import com.handong.rebon.exception.member.MemberNotFoundException;
import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.repository.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    //Review 메서드가 잘 돌아가는지 확인을 위해 임시로 만든 메서드
    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public MemberCreateResponseDto save(MemberCreateRequestDto memberCreateDto) {
        OauthProvider oauthProvider = OauthProvider.ignoreCase(memberCreateDto.getOauthProvider());
        String email = memberCreateDto.getEmail();
        String nickname = memberCreateDto.getNickname();
        boolean isAgreed = memberCreateDto.isAgreed();

        Member member = new Member(email, nickname, isAgreed, oauthProvider);

        Member savedMember = memberRepository.save(member);
        Long memberId = savedMember.getId();

        String token = jwtProvider.createToken(memberId);

        MemberCreateResponseDto response = new MemberCreateResponseDto(token, memberId);

        return response;
    }
}
