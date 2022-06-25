package com.handong.rebon.member.application;

import java.util.List;

import com.handong.rebon.auth.infrastructure.JwtProvider;
import com.handong.rebon.exception.member.MemberNotFoundException;
import com.handong.rebon.exception.member.NicknameDuplicateException;
import com.handong.rebon.exception.member.MemberNotFoundException;
import com.handong.rebon.member.application.dto.MemberDtoAssembler;
import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.application.dto.response.MemberReadResponseDto;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.repository.MemberRepository;
import com.handong.rebon.review.application.dto.ReviewDtoAssembler;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.tag.application.dto.TagDtoAssembler;
import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public MemberCreateResponseDto save(MemberCreateRequestDto memberCreateDto) {
        String oauthProvider = memberCreateDto.getOauthProvider();
        String email = memberCreateDto.getEmail();
        String nickname = memberCreateDto.getNickname();
        boolean isAgreed = memberCreateDto.isAgreed();

        Member member = new Member(email, nickname, isAgreed, oauthProvider);

        Member savedMember = memberRepository.save(member);
        Long memberId = savedMember.getId();

        String token = jwtProvider.createToken(String.valueOf(memberId));

        MemberCreateResponseDto response = new MemberCreateResponseDto(token, memberId);

        return response;
    }

    @Transactional(readOnly = true)
    public void checkNicknameDuplicate(String nickname) {
        boolean isDuplicated = memberRepository.existsByProfileNickname(nickname);
        if (isDuplicated) {
            throw new NicknameDuplicateException();
        }
    }

    @Transactional(readOnly = true)
    public MemberReadResponseDto findByMemberId(Long id) {
        Member member = findById(id);

        return MemberDtoAssembler.memberReadResponseDto(member);
    }

//    @Transactional(readOnly = true)
//    public List<MemberReadResponseDto> findInfos(){
//        return MemberDtoAssembler.memberReadResponseDtos(memberRepository.findAll());
//    }
}
