package com.handong.rebon.unit.review.domain;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewScore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewTest {

    @Test
    @DisplayName("리뷰에 공감한 사람 중에 유저가 있는 경우 true가 나온다.")
    void getTrueEmpathiesInUser() {
        //given
        Member member1 = createMember(1L, "peace");
        Member member2 = createMember(2L, "james");

        ReviewContent reviewContent = new ReviewContent("맛있어요", "족발 추천");

        Review review = Review.builder()
                              .reviewScore(new ReviewScore(5, 0))
                              .reviewContent(reviewContent)
                              .member(member1)
                              .build();
        review.empathize(member2);
        //when
        boolean isMemberLiked = review.isMemberLiked(member2);

        //then
        assertThat(isMemberLiked).isEqualTo(true);
    }

    @Test
    @DisplayName("리뷰에 공감한 사람 중에 유저가 없는 경우 false가 나온다.")
    void getFalseEmpathiesNotInUser() {
        //given
        Member member1 = createMember(1L, "peace");
        Member member2 = createMember(2L, "james");
        Member member3 = createMember(3L, "curry");

        ReviewContent reviewContent = new ReviewContent("맛있어요", "족발 추천");

        Review review = Review.builder()
                              .reviewScore(new ReviewScore(5, 0))
                              .reviewContent(reviewContent)
                              .member(member1)
                              .build();

        review.empathize(member2);

        //when
        boolean isMemberLiked = review.isMemberLiked(member3);

        //then
        assertThat(isMemberLiked).isEqualTo(false);
    }

    private Member createMember(Long id, String memberName) {
        return Member.builder()
                     .id(id)
                     .profile(new Profile("test@gmail.com", memberName))
                     .build();
    }
}
