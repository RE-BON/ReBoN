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

    public Profile(String nickName) {
        this.nickName = nickName;
    }
}
