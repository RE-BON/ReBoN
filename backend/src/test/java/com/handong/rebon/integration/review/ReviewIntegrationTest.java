package com.handong.rebon.integration.review;

import java.time.LocalTime;
import java.util.ArrayList;

import com.handong.rebon.exception.review.ReviewEmpathyExistException;
import com.handong.rebon.exception.review.ReviewEmpathyNotExistException;
import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;
import com.handong.rebon.member.domain.repository.MemberRepository;
import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.domain.Review;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        ReviewRequest reviewRequest = createReviewRequest("족발이 탱탱해요", "족발이랑 쟁반국수랑 시켜드세요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);

        //when

        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        //then
        assertThat(review.getReviewContent().getContent()).isEqualTo(reviewRequest.getContent());
        assertThat(review.getReviewContent().getTip()).isEqualTo(reviewRequest.getTip());
        assertThat(review.getReviewScore().getStar()).isEqualTo(reviewRequest.getStar());
        assertThat(review.getMember().getProfile().getNickname()).isEqualTo(member.getProfile().getNickname());
        assertThat(review.getShop().getShopContent().getName()).isEqualTo(shop.getShopContent().getName());

    }

    @Test
    @DisplayName("리뷰 공감")
    public void 리뷰_공감() {
        //given
        Review review = createReview("Byungwoong", "맘스터치");
        Member member = createMember("peace");
        //when
        review.empathize(member);
        //then
        assertThat(review.getEmpathyCount()).isEqualTo(1);
        assertThat(member.getEmpathies()).hasSize(1);
        assertThat(review.getEmpathies()).hasSize(1);
    }

    @Test
    @DisplayName("중복해서 리뷰를 공감할 시에 예외 발생")
    public void 리뷰_공감_중복_예외() {
        //given
        Review review = createReview("Byungwoong", "맘스터치");
        Member member = createMember("peace");
        review.empathize(member);
        //when, then
        assertThatThrownBy(() -> review.empathize(member))
                .isInstanceOf(ReviewEmpathyExistException.class);
    }

    @Test
    @DisplayName("리뷰 공감 취소")
    public void 리뷰_공감_취소() {
        //given
        Review review = createReview("Byungwoong", "맘스터치");
        Member member = createMember("peace");
        review.empathize(member);
        //when
        review.unEmpathize(member);
        //then
        assertThat(review.getEmpathyCount()).isEqualTo(0);
        assertThat(member.getEmpathies()).hasSize(0);
        assertThat(review.getEmpathies()).hasSize(0);
    }

    @Test
    @DisplayName("공감하지 않았는데 리뷰를 공감취소 할 시에 예외 발생")
    public void 리뷰_취소_예외() {
        //given
        Review review = createReview("Byungwoong", "맘스터치");
        Member member = createMember("peace");
        //when, then
        assertThatThrownBy(() -> review.unEmpathize(member))
                .isInstanceOf(ReviewEmpathyNotExistException.class);
    }

    @Test
    @DisplayName("중복해서 리뷰를 공감취소 할 시에 예외 발생")
    public void 리뷰_공감_취소_예외() {
        //given
        Review review = createReview("Byungwoong", "맘스터치");
        Member member = createMember("peace");
        review.empathize(member);
        review.unEmpathize(member);
        //when, then
        assertThatThrownBy(() -> review.unEmpathize(member))
                .isInstanceOf(ReviewEmpathyNotExistException.class);
    }

    public ReviewRequest createReviewRequest(String content, String tip, int star) {
        ReviewRequest reviewRequest = new ReviewRequest();
        ArrayList<String> imageUrls = new ArrayList<>();

        reviewRequest.setContent(content);
        reviewRequest.setTip(tip);
        reviewRequest.setStar(star);
        reviewRequest.setImageUrls(imageUrls);

        return reviewRequest;
    }

    protected Shop createShop(String shopName) {
        Shop shop = new Restaurant(
                null,
                null,
                new ShopContent(shopName, LocalTime.of(12, 0), LocalTime.of(23, 0), "010-1234-1212"),
                new ShopImages(),
                null,
                new ShopScore(0.0, 0), 1L,false
        );
        return shopRepository.save(shop);
    }

    protected Member createMember(String memberName) {
        Member member = Member.builder().profile(new Profile("test@test.com", memberName))
                              .isAgreed(true)
                              .oauthProvider("google")
                              .build();
        return memberRepository.save(member);
    }

    protected Review createReview(String memberName, String shopName) {
        Member member = createMember(memberName);
        Shop shop = createShop(shopName);
        ReviewRequest reviewRequest = createReviewRequest("불싸이버거 맛있어요", "야채 많이 달라하면 많이주셔요", 5);
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(member.getId(), shop.getId(), reviewRequest);
        Long id = reviewService.create(reviewCreateRequestDto);
        return reviewRepository.findById(id).get();
    }
}
