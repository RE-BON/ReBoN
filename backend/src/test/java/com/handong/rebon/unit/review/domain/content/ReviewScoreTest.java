package com.handong.rebon.unit.review.domain.content;

import com.handong.rebon.exception.review.ReviewLikeCountException;
import com.handong.rebon.exception.review.ReviewStarException;
import com.handong.rebon.review.domain.content.ReviewScore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReviewScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("review star에는 0 또는 음수가 들어갈 수 없다.")
    public void reviewStarNegativeException(int star) {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewScore(star, 5))
                .isInstanceOf(ReviewStarException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 7})
    @DisplayName("review star는 5점을 넘을 수 없다.")
    public void reviewStarExcessFiveException(int star) {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewScore(star, 0))
                .isInstanceOf(ReviewStarException.class);
    }

    @Test
    @DisplayName("review empathyCount에는 음수가 들어갈 수 없다.")
    public void reviewLikeCountNegativeException() {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewScore(1, -1))
                .isInstanceOf(ReviewLikeCountException.class);
    }

    @Test
    @DisplayName("empathyCount를 증가시킬 수 있다.")
    public void increaseEmpathyCount() {
        //given
        ReviewScore reviewScore = new ReviewScore(1, 0);
        //when
        reviewScore.increaseEmpathyCount();
        //then
        assertThat(reviewScore).extracting("empathyCount")
                               .isEqualTo(1);
    }

    @Test
    @DisplayName("empathyCount를 감소시킬 수 있다.")
    public void increaseEmpathyException() {
        //given
        ReviewScore reviewScore = new ReviewScore(1, 1);
        //when
        reviewScore.decreaseEmpathyCount();
        //then
        assertThat(reviewScore).extracting("empathyCount")
                               .isEqualTo(0);
    }

    @Test
    @DisplayName("empathyCount를 감소시킬 때 음수가 된다면 예외 발생")
    public void decreaseEmpathyCountException() {
        //given
        ReviewScore reviewScore = new ReviewScore(1, 0);
        //when, then
        assertThatThrownBy(reviewScore::decreaseEmpathyCount)
                .isInstanceOf(ReviewLikeCountException.class);
    }
}
