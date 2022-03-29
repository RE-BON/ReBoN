package com.handong.rebon.member.domain;

import java.util.List;
import javax.persistence.*;

import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.like.Like;

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
    private List<Like> likes;

    @OneToMany(mappedBy = "member")
    private List<Empathy> empathies;

    private boolean isAgreementMarketingReceptionTerm;
    private boolean isDeleted;

    public Member(Profile profile) {
        this.profile = profile;
    }

    public String getNickName() {
        return profile.getNickName();
    }
}