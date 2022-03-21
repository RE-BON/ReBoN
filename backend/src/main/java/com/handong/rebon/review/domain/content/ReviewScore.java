package com.handong.rebon.review.domain.content;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import com.handong.rebon.exception.review.ReviewLikeCountException;
import com.handong.rebon.exception.review.ReviewStarException;

@Getter
@NoArgsConstructor
@Embeddable
public class ReviewScore {
    private double star;
    private int empathyCount;

    public ReviewScore(double star, int empathyCount) {
        validatesRangeOfValues(star, empathyCount);
        this.star = star;
        this.empathyCount = empathyCount;
    }

    private void validatesRangeOfValues(double star, int likeCount) {
        if (star < 0 || star > 5) {
            throw new ReviewStarException();
        }
        if (likeCount < 0) {
            throw new ReviewLikeCountException();
        }
    }
}
