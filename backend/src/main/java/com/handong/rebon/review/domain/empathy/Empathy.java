package com.handong.rebon.review.domain.empathy;

import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.Review;

@Entity
public class Empathy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Review review;
}
