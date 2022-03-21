package com.handong.rebon.member;

import java.util.List;
import javax.persistence.*;

import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.member.domain.like.Like;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oauthId;

    @Embedded
    private Profile profile;

    @OneToMany(mappedBy = "member")
    private List<Like> likes;

    @OneToMany(mappedBy = "member")
    private List<Empathy> empathies;
}
