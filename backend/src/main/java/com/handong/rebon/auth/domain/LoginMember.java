package com.handong.rebon.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginMember {
    private static final LoginMember ANONYMOUS = new LoginMember();

    private Long id;

    public static LoginMember anonymous() {
        return ANONYMOUS;
    }

    public boolean isAnonymous() {
        return ANONYMOUS.equals(this);
    }
}
