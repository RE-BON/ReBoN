package com.handong.rebon.review.application;

import java.util.Arrays;
import java.util.List;

import com.handong.rebon.exception.member.MemberNotFoundException;
import com.handong.rebon.exception.review.ReviewNotFoundException;
import com.handong.rebon.exception.shop.ShopNotFoundException;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.repository.MemberRepository;
import com.handong.rebon.review.application.dto.ReviewDtoAssembler;
import com.handong.rebon.review.application.dto.request.*;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByMemberResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByShopResponseDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewImage;
import com.handong.rebon.review.domain.content.ReviewImages;
import com.handong.rebon.review.domain.repository.ReviewImageRepository;
import com.handong.rebon.review.domain.repository.ReviewRepository;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.util.StringUtil;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;
    private final MemberService memberService;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;

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


    @Transactional
    public void delete(ReviewDeleteRequestDto reviewDeleteRequestDto) {
        Long reviewId = reviewDeleteRequestDto.getReviewId();
        Long memberId = reviewDeleteRequestDto.getMemberId();

        Review review = findOneById(reviewId);
        Member member = memberService.findById(memberId);

        review.delete(member);
    }

    @Transactional(readOnly = true)
    public List<AdminReviewResponseDto> search(AdminReviewGetRequestDto adminReviewGetRequestDto) {
        String keyword = adminReviewGetRequestDto.getKeyword();
        Pageable pageable = adminReviewGetRequestDto.getPageable();

        if (StringUtils.hasText(keyword)) {
            String containingKeyword = StringUtil.makeContainingKeyword(keyword);
            List<Review> reviews = reviewRepository.searchReviewByKeywordApplyPage(
                                                           containingKeyword,
                                                           pageable)
                                                   .getContent();
            return ReviewDtoAssembler.adminReviewResponseDtos(reviews);
        }

        List<Review> reviews = reviewRepository.findAll(pageable).getContent();

        return ReviewDtoAssembler.adminReviewResponseDtos(reviews);
    }

    @Transactional(readOnly = true)
    public List<ReviewGetByMemberResponseDto> findAllByMember(ReviewGetByMemberRequestDto reviewGetByMemberRequestDto) {
        Long memberId = reviewGetByMemberRequestDto.getMemberId();
        Pageable pageable = reviewGetByMemberRequestDto.getPageable();

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        List<Review> reviews = reviewRepository.findAllByMember(member, pageable).getContent();

        return ReviewDtoAssembler.reviewGetByMemberResponseDtos(reviews);
    }

    @Transactional(readOnly = true)
    public List<ReviewGetByShopResponseDto> findAllByShop(ReviewGetByShopRequestDto reviewGetByShopRequestDto) {
        Long shopId = reviewGetByShopRequestDto.getShopId();
        Long memberId = reviewGetByShopRequestDto.getMemberId();
        Pageable pageable = reviewGetByShopRequestDto.getPageable();

        Shop shop = shopRepository.findById(shopId).orElseThrow(ShopNotFoundException::new);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        List<Review> reviews = reviewRepository.findAllByShop(shop, pageable).getContent();

        return ReviewDtoAssembler.reviewGetByShopResponseDtos(reviews, member);
    }

    @Transactional(readOnly = true)
    public AdminReviewResponseDto findByReviewId(Long reviewId) {
        Review review = findOneById(reviewId);

        return ReviewDtoAssembler.adminReviewResponseDto(review);
    }

    private Review findOneById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                        .orElseThrow(ReviewNotFoundException::new);
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
