package com.handong.rebon.member.application.dto.response;

import com.handong.rebon.member.domain.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberReadResponseDto {
    private Long id;
    private String image;
    private String email;
    private String nickName;
    private boolean isAgreed;

    public static MemberReadResponseDto from(Member member) {
        return new MemberReadResponseDto(member.getId(), member.getImage(), member.getNickName(), member.getEmail(), member
                .isAgreed());
    }
}
