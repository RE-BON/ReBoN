package com.handong.rebon.integration.review;

import com.handong.rebon.integration.IntegrationTest;
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewIntegrationTest extends IntegrationTest {

    @Autowired
    public ReviewService reviewService;

    @Autowired
    public ReviewRepository reviewRepository;

    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public ShopRepository shopRepository;

    @Test
    @DisplayName("리뷰 생성")
    void create() {
        //given
        Member member = createMember("peace");
        Shop shop = createShop("토시래");

        ReviewRequest reviewRequest = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요",5);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        //when

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        //then
        assertThat(review.getReviewContent().getContent()).isEqualTo(reviewRequest.getContent());
        assertThat(review.getReviewContent().getTip()).isEqualTo(reviewRequest.getTip());
        assertThat(review.getReviewScore().getStar()).isEqualTo(reviewRequest.getStar());
        assertThat(review.getMember().getProfile().getNickName()).isEqualTo(member.getProfile().getNickName());
        assertThat(review.getShop().getShopContent().getName()).isEqualTo(shop.getShopContent().getName());

    }


    public ReviewRequest createReviewRequest(String content, String tip, int star) {
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.setContent(content);
        reviewRequest.setTip(tip);
        reviewRequest.setStar(star);
        //reviewRequest.setImages();  TODO 이미지 저장 후 구현

        return reviewRequest;
    }

    protected Shop createShop(String shopName) {
        Shop shop = new Restaurant(null, null, new ShopContent(shopName, "12:00-23:00", "010-1234-1212"), new ShopImages(), null, new ShopScore(0.0, 0));
        return shopRepository.save(shop);
    }

    protected Member createMember(String memberName) {
        Member member = new Member(new Profile(memberName));
        return memberRepository.save(member);
    }
}
