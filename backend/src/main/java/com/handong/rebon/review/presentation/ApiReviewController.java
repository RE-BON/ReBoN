package com.handong.rebon.review.presentation;

import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.application.dto.response.ReviewResponseDto;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
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
}
