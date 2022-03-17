package com.handong.rebon.review.domain;

import java.util.List;
import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewImages;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.Shop;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Shop shop;

    @Embedded
    private ReviewContent reviewContent;

    @Embedded
    private ReviewImages reviewImages;

    @Embedded
    private ReviewScore reviewScore;

    @OneToMany(mappedBy = "review")
    private List<Empathy> empathies;

}
