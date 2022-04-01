package com.handong.rebon.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.like.Likes;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oauthId;

    @Embedded
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Likes> likes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Empathy> empathies = new ArrayList<>();

    private boolean isAgreed;

    private boolean isAdmin;

    public Member(Profile profile) {
        this.profile = profile;
    }

    public boolean isSame(Member member) {
        return this.id == member.id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
