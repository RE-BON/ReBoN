package com.handong.rebon.member.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Profile {
    private String nickName;
    private String image;
    private String email;

    public Profile(String email, String nickName) {
        this.email = email;
        this.nickName = nickName;
    }
}
