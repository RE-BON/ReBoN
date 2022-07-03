package com.handong.rebon.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequest {
    private String nickname;
    private boolean agreed;

    public MemberUpdateRequest(String nickname, boolean agreed) {
        this.nickname = nickname;
        this.agreed = agreed;
    }
}