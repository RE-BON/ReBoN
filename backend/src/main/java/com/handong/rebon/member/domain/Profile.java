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
    private String nickname;
    private String image;
    private String email;

    public Profile(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public void withdraw() {
        nickname = "anonymous";
        image = "";
        email = "";
    }
}
