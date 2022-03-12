package com.handong.rebon.review.domain.content;

import javax.persistence.Embeddable;

@Embeddable
public class ReviewScore {
    private double star;
    private int likeCount;
}
