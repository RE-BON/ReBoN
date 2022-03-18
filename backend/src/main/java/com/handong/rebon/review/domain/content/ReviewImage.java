package com.handong.rebon.review.domain.content;

import javax.persistence.*;

import com.handong.rebon.review.domain.Review;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    public ReviewImage(String url) {
        this.url = url;
    }
}
