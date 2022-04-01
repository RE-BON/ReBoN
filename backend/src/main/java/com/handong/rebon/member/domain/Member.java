package com.handong.rebon.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.like.Likes;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Member {

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
    private boolean isDeleted;

    public Member(Profile profile) {
        this.profile = profile;
    }

    public boolean isSame(Member member) {
        return id == member.id;
    }

    public void addEmpathy(Empathy empathy) {
        empathies.add(empathy);
        empathy.belongTo(this);
    }

    public String getNickName() {
        return profile.getNickName();
    }
}
