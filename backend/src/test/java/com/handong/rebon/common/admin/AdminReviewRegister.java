package com.handong.rebon.common.admin;

import java.util.List;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class AdminReviewRegister {
    @Autowired
    private ReviewService reviewService;

    public Review simpleRegister(Member member, Shop shop, String content) {
        ReviewCreateRequestDto createRequestDto = ReviewCreateRequestDto.builder()
                                                                        .memberId(member.getId())
                                                                        .shopId(shop.getId())
                                                                        .content(content)
                                                                        .tip("tip")
                                                                        .imageUrls(List.of("image1", "image2", "image3"))
                                                                        .star(5)
                                                                        .build();
        Long reviewId = reviewService.create(createRequestDto);
        return reviewService.findOneById(reviewId);
    }
}
