package com.handong.rebon.review.domain.empathy;

import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.Review;

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class Empathy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Review review;

    public Empathy(Member member, Review review) {
        this.member = member;
        this.review = review;
    }

    public boolean isSameMember(Member member) {
        return this.member.isSame(member);
    }

}
