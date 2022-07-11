package com.handong.rebon.shop.domain.content;

import java.util.List;
import javax.persistence.Embeddable;

import com.handong.rebon.review.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ShopScore {
    private double star;
    private int likeCount;
    private int reviewCount;

    public ShopScore(double star) {
        this.star = star;
    }

    public void plusReviewCount() {
        this.reviewCount++;
    }

    public void calculateStar(List<Review> reviews) {
        int total = 0;
        for (Review review : reviews) {
            total += review.getStar();
        }

        star = (double) total / reviewCount;
    }
}
