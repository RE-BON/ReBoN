package com.handong.rebon.review.presentation;

import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.review.presentation.dto.response.ReviewResponse;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ApiReviewController {
    private final ReviewService reviewService;

    @PostMapping("/shops/{shopId}/reviews")
    public ReviewResponse create(
            //member TODO 유저 구현 후,
            @PathVariable Long shopId,
            @RequestBody ReviewRequest reviewRequest
    ) {
        /*
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(, shopId, reviewRequest);//TODO user 구현 후 userId 구현해야됨
        ReviewResponseDto reviewResponseDto = reviewService.create(reviewCreateRequestDto);
        ReviewAssembler.reviewResponse(reviewResponseDto);
        */
        return new ReviewResponse();
    }

    @GetMapping("/shops/{shopId}/reviews")
    public ReviewResponse getReviewsByShop(
            //member // TODO 유저 구현 후,
            @PathVariable Long shopId
    ) {
        // TODO 유저 구현 후 user id 넘기기
        /*
         ReviewRequestDto reviewRequestDto = ReviewRequestDto.builder()
                                                             .shopId(shopId)
                                                             .memberId(memberId)
                                                             .build();
         List<ReviewResponseDto> reviews = reviewService.findByShopId(reviewRequestDto);
         */

        return new ReviewResponse();
    }

    @GetMapping("/my-reviews") // TODO api 정하기
    public ReviewResponse getReviewsByMember(
            //member TODO 유저 구현후
    ) {
        /* // TODO 유저 구현후
        ReviewRequestDto reviewRequestDto = ReviewRequestDto.builder()
                                                            .memberId(memberId)
                                                            .build();
        List<ReviewResponseDto> reviews = reviewService.findByMemberId(reviewRequestDto);
        */
        return new ReviewResponse();
    }
}
