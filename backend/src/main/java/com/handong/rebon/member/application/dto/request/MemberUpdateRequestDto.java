package com.handong.rebon.member.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateRequestDto {
    private Long memberId;
    private String nickname;
    private boolean agreed;
}