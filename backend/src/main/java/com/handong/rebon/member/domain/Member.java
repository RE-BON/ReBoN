package com.handong.rebon.member.domain;

import java.util.List;
import javax.persistence.*;

import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.like.Likes;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oauthId;

    @Embedded
    private Profile profile;

    @OneToMany(mappedBy = "member")
    private List<Likes> likes;

    @OneToMany(mappedBy = "member")
    private List<Empathy> empathies;

    public Member(Profile profile) {
        this.profile = profile;
    }

    public String getNickName() {
        return profile.getNickName();
    }
}
