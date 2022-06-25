package com.handong.rebon.integration.review;

import java.util.List;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.application.dto.request.*;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByMemberResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByShopResponseDto;
import com.handong.rebon.review.application.dto.response.TipGetByShopResponseDto;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.domain.PageRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewReadIntegrationTest extends ReviewIntegrationTest {

    @Test
    @DisplayName("content, tip에 모두 나쁜이라고 들어있는 삭제되지 않은 review를 가져온다.")
    public void searchReviewsByContentTitleTipContainsBad() {
        //given
        Member member = createMember("peace");
        Shop shop = createShop("토시래");

        ReviewRequest reviewRequest1 = createReviewRequest("나쁜족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest("여긴 막국수죠", "족발이랑 막국수랑 시켜드세요", 4);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest("나쁜 수육 맛없어요", "수육엔 김치죠", 3);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        deleteReview(member, review3Id);
        AdminReviewGetRequestDto adminReviewGetRequestDto = new AdminReviewGetRequestDto("나쁜", PageRequest.of(0, 10));

        //when
        List<AdminReviewResponseDto> reviews = reviewService.search(adminReviewGetRequestDto);


        //then
        assertThat(reviews).hasSize(1);
        assertThat(reviews).extracting("content")
                           .contains(reviewRequest1.getContent());

    }

    @Test
    @DisplayName("모든 삭제되지 않은 리뷰를 가져온다.")
    public void getReviews() {
        //given
        Member member = createMember("peace");
        Shop shop = createShop("토시래");

        ReviewRequest reviewRequest1 = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest("여긴 막국수죠", "족발이랑 막국수랑 시켜드세요", 4);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest("수육 맛없어요", "수육엔 김치죠", 1);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        deleteReview(member, review3Id);

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

        ReviewRequest reviewRequest1 = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member1.getId(), shop.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest("여긴 막국수죠", "족발이랑 막국수랑 시켜드세요", 4);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member2.getId(), shop.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest("수육 맛없어요", "수육엔 김치죠", 1);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member1.getId(), shop.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        deleteReview(member1, review3Id);

        ReviewGetByMemberRequestDto reviewGetByMemberRequestDto = ReviewGetByMemberRequestDto.builder()
                                                                                             .memberId(member1.getId())
                                                                                             .build();

        //when
        List<ReviewGetByMemberResponseDto> reviews = reviewService.findAllByMember(reviewGetByMemberRequestDto);

        //then
        assertThat(reviews).hasSize(1);
        assertThat(reviews).extracting("content")
                           .contains(reviewRequest1.getContent());
    }

    @Test
    @DisplayName("토시래 삭제되지 않은 리뷰만 가져온다.")
    void getReviewByShop() {
        //given
        Member member = createMember("peace");
        Shop shop1 = createShop("토시래");
        Shop shop2 = createShop("팜스발리");

        ReviewRequest reviewRequest1 = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop1.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest("피자 맛이 좋아요", "치킨이랑 피자 시켜드세요", 4);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop2.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest("토시래 족발 굳", "앞족발 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop1.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        Long review3Id = reviewService.create(reviewCreateRequestDto3);

        deleteReview(member, review3Id);

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

        ReviewRequest reviewRequest = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        Long reviewId = reviewService.create(reviewCreateRequestDto);

        //when
        AdminReviewResponseDto review = reviewService.findByReviewId(reviewId);

        //then
        assertThat(review).extracting("content")
                          .isEqualTo(reviewRequest.getContent());
        assertThat(review).extracting("tip")
                          .isEqualTo(reviewRequest.getTip());
    }

    public void deleteReview(Member member, Long reviewId) {
        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(member.getId(), reviewId);
        reviewService.delete(reviewDeleteRequestDto);
    }

    @Test
    @DisplayName("나만의 꿀팁을 포함하는 리뷰만 조회한다.")
    public void getTipByShop() {
        //given
        Member member = createMember("peace");
        Shop shop1 = createShop("토시래");
        Shop shop2 = createShop("팜스발리");

        ReviewRequest reviewRequest1 = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto1 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop1.getId(), reviewRequest1);

        ReviewRequest reviewRequest2 = createReviewRequest("피자 맛이 좋아요", "치킨이랑 피자 시켜드세요", 4);
        ReviewCreateRequestDto reviewCreateRequestDto2 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop2.getId(), reviewRequest2);

        ReviewRequest reviewRequest3 = createReviewRequest("토시래 족발 굳", null, 5);
        ReviewCreateRequestDto reviewCreateRequestDto3 = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop1.getId(), reviewRequest3);

        reviewService.create(reviewCreateRequestDto1);
        reviewService.create(reviewCreateRequestDto2);
        reviewService.create(reviewCreateRequestDto3);

        TipGetByShopRequestDto tipGetByShopRequestDto = TipGetByShopRequestDto.builder()
                                                                              .shopId(shop1.getId())
                                                                              .build();

        //when
        List<TipGetByShopResponseDto> tips = reviewService.findAllByShopContainTips(tipGetByShopRequestDto);

        //then
        assertThat(tips).hasSize(1);
        assertThat(tips).extracting("shopName")
                        .contains(shop1.getName());
        assertThat(tips).extracting("tip")
                        .contains(reviewRequest1.getTip());
    }
}
