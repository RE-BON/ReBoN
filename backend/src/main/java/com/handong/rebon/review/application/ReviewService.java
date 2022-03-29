package com.handong.rebon.review.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.handong.rebon.exception.member.MemberNotFoundException;
import com.handong.rebon.exception.review.ReviewNotFoundException;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.repository.MemberRepository;
import com.handong.rebon.review.application.dto.ReviewDtoAssembler;
import com.handong.rebon.review.application.dto.request.AdminReviewGetRequestDto;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.application.dto.request.ReviewRequestDto;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewResponseDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewImage;
import com.handong.rebon.review.domain.content.ReviewImages;
import com.handong.rebon.review.domain.repository.ReviewImageRepository;
import com.handong.rebon.review.domain.repository.ReviewRepository;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final ShopService shopService;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(ReviewCreateRequestDto reviewCreateRequestDto) {
        //TODO 멤버 찾아오기
        Member member = memberService.findById(reviewCreateRequestDto.getMemberId());

        //TODO shop 찾아오기
        Shop shop = shopService.findById(reviewCreateRequestDto.getShopId());

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

    @Transactional
    public List<AdminReviewResponseDto> search(AdminReviewGetRequestDto adminReviewGetRequestDto) {
        String keyword = adminReviewGetRequestDto.getKeyword();
        Pageable pageable = adminReviewGetRequestDto.getPageable();

        List<Review> reviews = new ArrayList<>();

        if (keyword == null) {
            reviews = reviewRepository.findAll(pageable).toList();
            return ReviewDtoAssembler.adminReviewResponseDtos(reviews);
        }

        reviews.addAll(reviewRepository.findAllByReviewContentContaining(makeContainingKeyword(keyword), pageable));

        return ReviewDtoAssembler.adminReviewResponseDtos(reviews);
    }

    @Transactional
    public List<ReviewResponseDto> findByMemberId(ReviewRequestDto reviewRequestDto) {
        Long memberId = reviewRequestDto.getMemberId();
        Pageable pageable = reviewRequestDto.getPageable();

        List<Review> reviews = reviewRepository.findAllByMemberId(memberId, pageable);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        return ReviewDtoAssembler.reviewResponseDtos(reviews, member);
    }

    @Transactional
    public List<ReviewResponseDto> findByShopId(ReviewRequestDto reviewRequestDto) {
        Long shopId = reviewRequestDto.getShopId();
        Long memberId = reviewRequestDto.getMemberId();
        Pageable pageable = reviewRequestDto.getPageable();

        List<Review> reviews = reviewRepository.findAllByShopId(shopId, pageable);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        return ReviewDtoAssembler.reviewResponseDtos(reviews, member);
    }

    @Transactional
    public AdminReviewResponseDto findByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                                        .orElseThrow(ReviewNotFoundException::new);

        return ReviewDtoAssembler.adminReviewResponseDto(review);
    }

    //TODO 이미지 저장 기능
    private ReviewImages saveImages(List<MultipartFile> images) {
        ReviewImage url1 = new ReviewImage("url1");
        ReviewImage url2 = new ReviewImage("url2");

        reviewImageRepository.save(url1);
        reviewImageRepository.save(url2);

        return new ReviewImages(Arrays.asList(url1, url2));
    }

    private String makeContainingKeyword(String keyword) {
        return "%" + keyword + "%";
    }
}
