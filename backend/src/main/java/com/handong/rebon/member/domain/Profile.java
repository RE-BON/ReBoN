package com.handong.rebon.member.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Profile {
    private String nickName;
    private String image;
    private String email;

    public Profile(String nickName) {
        this.nickName = nickName;
    }
}
