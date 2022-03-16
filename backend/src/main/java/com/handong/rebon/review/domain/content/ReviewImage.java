package com.handong.rebon.review.domain.content;

import javax.persistence.*;

import com.handong.rebon.review.domain.Review;

@Entity
public class ReviewImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;
}
