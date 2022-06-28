package com.handong.rebon.review.domain.content;

import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.review.domain.Review;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    public ReviewImage(String url) {
        this.url = url;
    }

    public void addReview(Review review) {
        this.review = review;
    }
}
