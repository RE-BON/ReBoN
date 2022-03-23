package com.handong.rebon.integration.review;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;
import com.handong.rebon.member.domain.repository.MemberRepository;
import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.domain.repository.ReviewRepository;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReviewIntegrationTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("리뷰 생성")
    void create() {
        //given
        Member member = createMember();
        Shop shop = createShop();

        ReviewContent reviewContent = new ReviewContent("맛있어요", "족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요");
        ReviewScore reviewScore = new ReviewScore(5, 0);

        ReviewRequest reviewRequest = createReviewRequest(reviewContent, reviewScore);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        //when
        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        //then
        assertThat(review.getReviewContent().getTitle()).isEqualTo(reviewContent.getTitle());
        assertThat(review.getReviewContent().getContent()).isEqualTo(reviewContent.getContent());
        assertThat(review.getReviewContent().getTip()).isEqualTo(reviewContent.getTip());
        assertThat(review.getReviewScore().getStar()).isEqualTo(reviewScore.getStar());
        assertThat(review.getMember().getProfile().getNickName()).isEqualTo(member.getProfile().getNickName());
        assertThat(review.getShop().getShopContent().getName()).isEqualTo(shop.getShopContent().getName());

    }

    private ReviewRequest createReviewRequest(ReviewContent reviewContent, ReviewScore reviewScore) {
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.setTitle(reviewContent.getTitle());
        reviewRequest.setContent(reviewContent.getContent());
        reviewRequest.setTip(reviewContent.getTip());
        reviewRequest.setStar(reviewScore.getStar());
        //reviewRequest.setImages();  TODO 이미지 저장 후 구현

        return reviewRequest;
    }

    private Shop createShop() {

        Shop shop = new Restaurant(null, null, new ShopContent("토시래", "12:00-23:00", "010-1234-1212"), new ShopImages(), null, new ShopScore(0.0, 0));
        return shopRepository.save(shop);
    }

    private Member createMember() {
        Member member = new Member(new Profile("peace"));
        return memberRepository.save(member);
    }
}