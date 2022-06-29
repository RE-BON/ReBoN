package com.handong.rebon.integration.review;

import java.util.ArrayList;

import com.handong.rebon.exception.member.MemberForbiddenException;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.application.dto.request.ReviewUpdateRequestDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.shop.domain.Shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReviewUpdateIntegrationTest extends ReviewIntegrationTest {

    @Test
    @DisplayName("리뷰 작성자가 자신의 리뷰 수정")
    void updateByAuthor() {
        //given
        Member member = createMember("peace", false);
        Shop shop = createShop("토시래");

        ReviewRequest reviewRequest = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        ReviewUpdateRequestDto reviewUpdateRequestDto = ReviewUpdateRequestDto.builder()
                                                                              .memberId(member.getId())
                                                                              .reviewId(review.getId())
                                                                              .content("족발이 탱탱합니다")
                                                                              .tip("족발이랑 쟁반국수 시켜드세요")
                                                                              .imageUrls(new ArrayList<>())
                                                                              .star(4)
                                                                              .build();

        //when
        reviewService.update(reviewUpdateRequestDto);
        Review updatedReview = reviewRepository.findById(review.getId()).get();

        //then
        assertThat(updatedReview)
                .extracting("star")
                .isEqualTo(reviewUpdateRequestDto.getStar());
    }

    @Test
    @DisplayName("어드민에 의해 리뷰 수정")
    void updateByAdmin() {
        //given
        Member member = createMember("peace", false);
        Member admin = createMember("admin", true);
        Shop shop = createShop("토시래");

        ReviewRequest reviewRequest = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        ReviewUpdateRequestDto reviewUpdateRequestDto = ReviewUpdateRequestDto.builder()
                                                                              .memberId(admin.getId())
                                                                              .reviewId(review.getId())
                                                                              .content("족발이 탱탱합니다")
                                                                              .tip("족발이랑 쟁반국수 시켜드세요")
                                                                              .imageUrls(new ArrayList<>())
                                                                              .star(4)
                                                                              .build();

        //when
        reviewService.update(reviewUpdateRequestDto);
        Review updatedReview = reviewRepository.findById(review.getId()).get();

        //then
        assertThat(updatedReview)
                .extracting("star")
                .isEqualTo(reviewUpdateRequestDto.getStar());
    }

    @Test
    @DisplayName("어드민이나 리뷰 작성자가 아니라면 exception이 발생한다.")
    void notAuthorOrNotAdminThrowsException() {
        //given
        Member member = createMember("peace", false);
        Member curry = createMember("curry", false);
        Shop shop = createShop("토시래");

        ReviewRequest reviewRequest = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        ReviewUpdateRequestDto reviewUpdateRequestDto = ReviewUpdateRequestDto.builder()
                                                                              .memberId(curry.getId())
                                                                              .reviewId(review.getId())
                                                                              .content("족발이 탱탱합니다")
                                                                              .tip("족발이랑 쟁반국수 시켜드세요")
                                                                              .imageUrls(new ArrayList<>())
                                                                              .star(4)
                                                                              .build();

        //when
        //then
        assertThatThrownBy(() -> reviewService.update(reviewUpdateRequestDto))
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
