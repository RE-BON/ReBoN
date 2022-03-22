package com.handong.rebon.unit.review.domain.content;

import com.handong.rebon.exception.review.ReviewContentFormatException;
import com.handong.rebon.exception.review.ReviewTitleFormatException;
import com.handong.rebon.review.domain.content.ReviewContent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ReviewContentTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("리뷰의 title은 null이거나 빈 문자열(공백만 있는 문자열 포함)이면 생성할 수 없다.")
    public void reviewTitleIsNullOrEmptyException(String title) {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewContent(title, "너무 좋아요"))
                .isInstanceOf(ReviewTitleFormatException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("리뷰의 content는 null이거나 빈 문자열(공백만 있는 문자열 포함)이면 생성할 수 없다.")
    public void reviewContentIsNullOrEmptyException(String content) {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewContent("맛집입니다.", content))
                .isInstanceOf(ReviewContentFormatException.class);
    }
}