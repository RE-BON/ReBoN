package com.handong.rebon.review.presentation;

import java.net.URI;
import java.util.List;

import com.handong.rebon.auth.domain.Login;
import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.auth.domain.RequiredLogin;
import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.application.dto.request.ReviewDeleteRequestDto;
import com.handong.rebon.review.application.dto.request.ReviewGetByShopRequestDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByShopResponseDto;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.review.presentation.dto.response.ReviewGetByShopResponse;
import com.handong.rebon.review.presentation.dto.response.ReviewResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ApiReviewController {
    private final ReviewService reviewService;

    @RequiredLogin
    @PostMapping("/shops/{shopId}/reviews")
    public ResponseEntity<Void> create(
            @Login LoginMember loginMember,
            @PathVariable Long shopId,
            @RequestBody ReviewRequest reviewRequest
    ) {

        ReviewCreateRequestDto reviewCreateRequestDto = ReviewAssembler.reviewCreateRequestDto(loginMember.getId(), shopId, reviewRequest);
        Long reviewId = reviewService.create(reviewCreateRequestDto);
        URI location = URI.create("/api/reviews/" + reviewId);

        return ResponseEntity.created(location)
                             .build();
    }

    @RequiredLogin
    @GetMapping("/shops/{shopId}/reviews")
    public ResponseEntity<List<ReviewGetByShopResponse>> getReviewsByShop(
            @Login LoginMember loginMember,
            @PathVariable Long shopId
    ) {
        // TODO 유저 구현 후 user id 넘기기

        ReviewGetByShopRequestDto reviewGetByShopRequestDto = ReviewGetByShopRequestDto.builder()
                                                                                       .shopId(shopId)
                                                                                       .memberId(loginMember.getId())
                                                                                       .build();
        List<ReviewGetByShopResponseDto> reviews = reviewService.findAllByShop(reviewGetByShopRequestDto);

        return ResponseEntity.ok(ReviewGetByShopResponse.convert(reviews));
    }

    @RequiredLogin
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

    @RequiredLogin
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> delete(
            @Login LoginMember loginMember,
            @PathVariable Long reviewId
    ) {
        ReviewDeleteRequestDto reviewDeleteRequestDto = new ReviewDeleteRequestDto(loginMember.getId(), reviewId);
        reviewService.delete(reviewDeleteRequestDto);

        return ResponseEntity.ok()
                             .build();
    }
}
