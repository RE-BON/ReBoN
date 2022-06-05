package com.handong.rebon.review.domain.content;

import javax.persistence.Embeddable;

import com.handong.rebon.exception.review.ReviewLikeCountException;
import com.handong.rebon.exception.review.ReviewStarException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class ReviewScore {
    private int star;
    private int empathyCount;

    public ReviewScore(int star, int empathyCount) {
        validatesRangeOfValues(star, empathyCount);
        this.star = star;
        this.empathyCount = empathyCount;
    }

    private void validatesRangeOfValues(int star, int likeCount) {
        if (star <= 0 || star > 5) {
            throw new ReviewStarException();
        }
        if (likeCount < 0) {
            throw new ReviewLikeCountException();
        }
    }

    public void increaseEmpathyCount() {
        this.empathyCount++;
    }

    public void decreaseEmpathyCount() {
        if (this.empathyCount <= 0) {
            throw new ReviewLikeCountException();
        }
        this.empathyCount--;
    }
}
