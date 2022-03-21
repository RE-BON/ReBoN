package com.handong.rebon.review.application;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;
import com.handong.rebon.member.domain.repository.MemberRepository;
import com.handong.rebon.review.application.request.ReviewCreateRequestDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.domain.repository.ReviewRepository;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.item.Shop;
import com.handong.rebon.shop.domain.repository.ShopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

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
        ReviewScore reviewScore = new ReviewScore(4.5, 0);

        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                                                                              .memberId(member.getId())
                                                                              .shopId(shop.getId())
                                                                              .title(reviewContent.getTitle())
                                                                              .content(reviewContent.getContent())
                                                                              .tip(reviewContent.getTip())
                                                                              .star(reviewScore.getStar())
                                                                              //.images() TODO 이미지 저장 구현시 추가
                                                                              .build();

        //when
        Long id = reviewService.create(reviewCreateRequestDto);
        Review review = reviewRepository.findById(id).get();

        //then
        assertThat(review.getTitle()).isEqualTo(reviewContent.getTitle());
        assertThat(review.getContent()).isEqualTo(reviewContent.getContent());
        assertThat(review.getTip()).isEqualTo(reviewContent.getTip());
        assertThat(review.getStar()).isEqualTo(reviewScore.getStar());
        assertThat(review.getAuthorName()).isEqualTo(member.getProfile().getNickName());
        assertThat(review.getShopName()).isEqualTo(shop.getShopContent().getName());

    }

    private Shop createShop() {
        Shop shop = new Shop(new ShopContent("토시래", "12:00-23:00", "010-1234-1212"), new ShopScore(0.0, 0));
        return shopRepository.save(shop);
    }

    private Member createMember() {
        Member member = new Member(new Profile("peace"));
        return memberRepository.save(member);
    }
}