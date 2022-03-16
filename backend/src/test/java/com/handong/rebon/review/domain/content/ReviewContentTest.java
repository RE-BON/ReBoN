package com.handong.rebon.review.domain.content;

import com.handong.rebon.exception.review.ReviewContentFormatException;
import com.handong.rebon.exception.review.ReviewTitleFormatException;
import com.handong.rebon.review.domain.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ReviewContentTest {
    
    @Test
    @DisplayName("리뷰의 title은 null이거나 빈 문자열(공백만 있는 문자열 포함)이면 생성할 수 없다.")
    public void reviewTitleIsNullOrEmptyException() {
        //given
        //when, then
        assertThatThrownBy(() -> Review.builder().reviewContent(new ReviewContent("", "너무 좋아요"))
                .build())
                .isInstanceOf(ReviewTitleFormatException.class);
    }

    @Test
    @DisplayName("리뷰의 content는 null이거나 빈 문자열(공백만 있는 문자열 포함)이면 생성할 수 없다.")
    public void reviewContentIsNullOrEmptyException() {
        //given
        //when, then
        assertThatThrownBy(() -> Review.builder().reviewContent(new ReviewContent("맛집입니다.", null))
                .build())
                .isInstanceOf(ReviewContentFormatException.class);
    }
}