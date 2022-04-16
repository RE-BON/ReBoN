package com.handong.rebon.member.presentation.dto.request;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {
    @NotNull
    private String email;
    @NotNull
    private String nickname;
    @NotNull
    private String oauthProvider;
    @NotNull
    private boolean agreed;
}
