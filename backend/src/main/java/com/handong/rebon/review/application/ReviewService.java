package com.handong.rebon.review.application;

import java.util.Arrays;
import java.util.List;

import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewImage;
import com.handong.rebon.review.domain.content.ReviewImages;
import com.handong.rebon.review.domain.repository.ReviewImageRepository;
import com.handong.rebon.review.domain.repository.ReviewRepository;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.repository.ShopRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;
    private final MemberService memberService;
    private final ShopService shopService;
    private final ReviewImageRepository reviewImageRepository;

    @Transactional
    public Long create(ReviewCreateRequestDto reviewCreateRequestDto) {
        //TODO 멤버 찾아오기
        Member member = memberService.findById(reviewCreateRequestDto.getMemberId());

        //TODO shop 찾아오기
        Shop shop = shopRepository.findById(reviewCreateRequestDto.getShopId()).orElseThrow();

        //TODO 이미지 저장
        ReviewImages reviewImages = saveImages(reviewCreateRequestDto.getImages());

        Review review = Review.builder()
                              .member(member)
                              .shop(shop)
                              .reviewContent(reviewCreateRequestDto.getReviewContent())
                              .reviewScore(reviewCreateRequestDto.getReviewScore())
                              .build();

        review.addReviewImages(reviewImages);
        Review savedReview = reviewRepository.save(review);

        return savedReview.getId();
    }

    //TODO 이미지 저장 기능
    private ReviewImages saveImages(List<MultipartFile> images) {
        ReviewImage url1 = new ReviewImage("url1");
        ReviewImage url2 = new ReviewImage("url2");

        reviewImageRepository.save(url1);
        reviewImageRepository.save(url2);

        return new ReviewImages(Arrays.asList(url1, url2));
    }

}
