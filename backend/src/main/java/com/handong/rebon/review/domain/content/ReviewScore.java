package com.handong.rebon.review.domain.content;

import lombok.AllArgsConstructor;
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
    private int likeCount; // empathyCount로 바꾸는 건 어떨까?

    public ReviewScore(double star, int likeCount) {
        validatesNegative(star,likeCount);
        this.star = star;
        this.likeCount = likeCount;
    }

    private void validatesNegative(double star, int likeCount) {
        if(star < 0) {
            throw new ReviewStarException();
        }
        if(likeCount < 0) {
            throw new ReviewLikeCountException();
        }
    }
}
