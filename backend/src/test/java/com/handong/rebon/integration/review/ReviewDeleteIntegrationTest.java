package com.handong.rebon.integration.review;

import com.handong.rebon.exception.member.MemberForbiddenException;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.application.dto.request.ReviewDeleteRequestDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.shop.domain.Shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReviewDeleteIntegrationTest extends ReviewIntegrationTest {

    @Test
    @DisplayName("리뷰 작성자가 자신의 리뷰 삭제")
    void deleteByAuthor() {
        //given
        Member member = createMember("peace", false);
        Shop shop = createShop("토시래");

        ReviewContent reviewContent = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore = new ReviewScore(5, 0);

        ReviewRequest reviewRequest = createReviewRequest(reviewContent, reviewScore);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(member.getId(), review.getId());

        //when
        reviewService.delete(reviewDeleteRequestDto);
        Review deletedReview = reviewRepository.findById(review.getId()).get();

        //then
        assertThat(deletedReview)
                .extracting("isDeleted")
                .isEqualTo(true);

    }

    @Test
    @DisplayName("어드민에 의해 리뷰 삭제")
    void deleteByAdmin() {
        //given
        Member member = createMember("peace", false);
        Member admin = createMember("admin", true);
        Shop shop = createShop("토시래");

        ReviewContent reviewContent = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore = new ReviewScore(5, 0);

        ReviewRequest reviewRequest = createReviewRequest(reviewContent, reviewScore);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(admin.getId(), review.getId());

        //when
        reviewService.delete(reviewDeleteRequestDto);
        Review deletedReview = reviewRepository.findById(review.getId()).get();

        //then
        assertThat(deletedReview)
                .extracting("isDeleted")
                .isEqualTo(true);
    }

    @Test
    @DisplayName("어드민이나 리뷰 작성자가 아니라면 exception이 발생한다.")
    void notAuthorOrNotAdminThrowsException() {
        //given
        Member member = createMember("peace", false);
        Member curry = createMember("curry", false);
        Shop shop = createShop("토시래");

        ReviewContent reviewContent = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore = new ReviewScore(5, 0);

        ReviewRequest reviewRequest = createReviewRequest(reviewContent, reviewScore);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(curry.getId(), review.getId());

        //when
        //then
        assertThatThrownBy(() -> reviewService.delete(reviewDeleteRequestDto))
                .isInstanceOf(MemberForbiddenException.class);
    }

    public Member createMember(String name, boolean isAdmin) {
        Member member = Member.builder()
                              .profile(new Profile("test@gmail.com", name))
                              .isAdmin(isAdmin)
                              .build();

        return memberRepository.save(member);
    }
}
