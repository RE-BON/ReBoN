package com.handong.rebon.integration.review;

import java.util.List;

import com.handong.rebon.exception.review.ReviewNotFoundException;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.application.dto.request.*;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByMemberResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByShopResponseDto;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.domain.PageRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewReadIntegrationTest extends ReviewIntegrationTest {

    @Test
    @DisplayName("content, tip에 모두 나쁜이라고 들어있는 삭제되지 않은 review를 가져온다.")
    public void searchReviewsByContentTitleTipContainsBad() {
        //given
        Member member = createMember("peace");
        Shop shop = createShop("토시래");

        ReviewContent reviewContent1 = new ReviewContent("나쁜족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore1 = new ReviewScore(5, 0);

        ReviewContent reviewContent2 = new ReviewContent("여긴 막국수죠", "족발이랑 막국수랑 시켜드세요");
        ReviewScore reviewScore2 = new ReviewScore(4, 0);

        ReviewContent reviewContent3 = new ReviewContent("나쁜 수육 맛없어요", "수육엔 김치죠");
        ReviewScore reviewScore3 = new ReviewScore(3, 0);

        ReviewRequest reviewRequest1 = createReviewRequest(reviewContent1, reviewScore1);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest(reviewContent2, reviewScore2);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest(reviewContent3, reviewScore3);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(member.getId(), review3Id);
        reviewService.delete(reviewDeleteRequestDto);

        AdminReviewGetRequestDto adminReviewGetRequestDto = new AdminReviewGetRequestDto("나쁜", PageRequest.of(0, 10));

        //when
        List<AdminReviewResponseDto> reviews = reviewService.search(adminReviewGetRequestDto);


        //then
        assertThat(reviews).hasSize(1);
        assertThat(reviews).extracting("content")
                           .contains(reviewContent1.getContent());

    }

    @Test
    @DisplayName("모든 삭제되지 않은 리뷰를 가져온다.")
    public void getReviews() {
        //given
        Member member = createMember("peace");
        Shop shop = createShop("토시래");

        ReviewContent reviewContent1 = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore1 = new ReviewScore(5, 0);

        ReviewContent reviewContent2 = new ReviewContent("여긴 막국수죠", "족발이랑 막국수랑 시켜드세요");
        ReviewScore reviewScore2 = new ReviewScore(4, 0);

        ReviewContent reviewContent3 = new ReviewContent("수육 맛없어요", "수육엔 김치죠");
        ReviewScore reviewScore3 = new ReviewScore(1, 0);

        ReviewRequest reviewRequest1 = createReviewRequest(reviewContent1, reviewScore1);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest(reviewContent2, reviewScore2);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest(reviewContent3, reviewScore3);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(member.getId(), review3Id);
        reviewService.delete(reviewDeleteRequestDto);

        AdminReviewGetRequestDto adminReviewGetRequestDto = new AdminReviewGetRequestDto(null, PageRequest.of(0, 10));

        //when
        List<AdminReviewResponseDto> reviews = reviewService.search(adminReviewGetRequestDto);

        //then
        assertThat(reviews).hasSize(2);
        assertThat(reviews).extracting("authorName")
                           .contains(member.getNickName());
    }

    @Test
    @DisplayName("peace가 쓴 삭제되지 않은 리뷰들만 가져온다.")
    void getReviewsByMember() {
        //given
        Member member1 = createMember("peace");
        Shop shop = createShop("토시래");
        Member member2 = createMember("pg13");

        ReviewContent reviewContent1 = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore1 = new ReviewScore(5, 0);

        ReviewContent reviewContent2 = new ReviewContent("여긴 막국수죠", "족발이랑 막국수랑 시켜드세요");
        ReviewScore reviewScore2 = new ReviewScore(4, 0);

        ReviewContent reviewContent3 = new ReviewContent("수육 맛없어요", "수육엔 김치죠");
        ReviewScore reviewScore3 = new ReviewScore(1, 0);

        ReviewRequest reviewRequest1 = createReviewRequest(reviewContent1, reviewScore1);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member1.getId(), shop.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest(reviewContent2, reviewScore2);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member2.getId(), shop.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest(reviewContent3, reviewScore3);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member1.getId(), shop.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(member1.getId(), review3Id);
        reviewService.delete(reviewDeleteRequestDto);

        ReviewGetByMemberRequestDto reviewGetByMemberRequestDto = ReviewGetByMemberRequestDto.builder()
                                                                                             .memberId(member1.getId())
                                                                                             .build();

        //when
        List<ReviewGetByMemberResponseDto> reviews = reviewService.findAllByMember(reviewGetByMemberRequestDto);

        //then
        assertThat(reviews).hasSize(1);
        assertThat(reviews).extracting("content")
                           .contains(reviewContent1.getContent());
    }

    @Test
    @DisplayName("토시래 삭제되지 않은 리뷰만 가져온다.")
    void getReviewByShop() {
        //given
        Member member = createMember("peace");
        Shop shop1 = createShop("토시래");
        Shop shop2 = createShop("팜스발리");

        ReviewContent reviewContent1 = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore1 = new ReviewScore(5, 0);

        ReviewContent reviewContent2 = new ReviewContent("피자 맛이 좋아요", "치킨이랑 피자 시켜드세요");
        ReviewScore reviewScore2 = new ReviewScore(4, 0);

        ReviewContent reviewContent3 = new ReviewContent("토시래 족발 굳", "앞족발 시켜드세요");
        ReviewScore reviewScore3 = new ReviewScore(5, 0);


        ReviewRequest reviewRequest1 = createReviewRequest(reviewContent1, reviewScore1);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop1.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest(reviewContent2, reviewScore2);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop2.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest(reviewContent3, reviewScore3);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop1.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(member.getId(), review3Id);
        reviewService.delete(reviewDeleteRequestDto);

        ReviewGetByShopRequestDto reviewGetByShopRequestDto = ReviewGetByShopRequestDto.builder()
                                                                                       .shopId(shop1.getId())
                                                                                       .memberId(member.getId())
                                                                                       .build();

        //when
        List<ReviewGetByShopResponseDto> reviews = reviewService.findAllByShop(reviewGetByShopRequestDto);

        //then
        assertThat(reviews).hasSize(1);
        assertThat(reviews).extracting("shopName")
                           .contains(shop1.getName());
    }

    @Test
    @DisplayName("하나의 삭제되지 않은 리뷰만 가져온다.")
    void getReview() {
        //given
        Member member = createMember("peace");
        Shop shop = createShop("토시래");

        ReviewContent reviewContent = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore = new ReviewScore(5, 0);

        ReviewRequest reviewRequest = createReviewRequest(reviewContent, reviewScore);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long reviewId = reviewService.create(reviewCreateRequestDto);

        //when
        AdminReviewResponseDto review = reviewService.findByReviewId(reviewId);

        //then
        assertThat(review).extracting("content")
                          .isEqualTo(reviewContent.getContent());
        assertThat(review).extracting("tip")
                          .isEqualTo(reviewContent.getTip());
    }

    @Test
    @DisplayName("삭제된 리뷰를 요청하면 exception이 발생한다.")
    void getDeletedReviewException() {
        //given
        Member member = createMember("peace");
        Shop shop = createShop("토시래");

        ReviewContent reviewContent = new ReviewContent("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore = new ReviewScore(5, 0);

        ReviewRequest reviewRequest = createReviewRequest(reviewContent, reviewScore);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long reviewId = reviewService.create(reviewCreateRequestDto);

        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(member.getId(), reviewId);
        reviewService.delete(reviewDeleteRequestDto);

        //when,then
        Assertions.assertThatThrownBy(() -> reviewService.findByReviewId(reviewId))
                  .isInstanceOf(ReviewNotFoundException.class);
    }
}