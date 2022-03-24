package com.handong.rebon.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

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