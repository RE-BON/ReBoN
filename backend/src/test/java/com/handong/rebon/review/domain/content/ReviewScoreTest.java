package com.handong.rebon.review.domain.content;

import com.handong.rebon.exception.review.ReviewLikeCountException;
import com.handong.rebon.exception.review.ReviewStarException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReviewScoreTest {

    @Test
    @DisplayName("review star에는 음수가 들어갈 수 없다.")
    public void reviewStarNegativeException() {
        //given
        //when, then

        assertThatThrownBy(() -> new ReviewScore(-1, 5))
                .isInstanceOf(ReviewStarException.class);
    }

    @Test
    @DisplayName("review likeCount에는 음수가 들어갈 수 없다.")
    public void reviewLikeCountNegativeException() {
        //given
        //when, then

        assertThatThrownBy(() -> new ReviewScore(0, -1))
                .isInstanceOf(ReviewLikeCountException.class);
    }
}