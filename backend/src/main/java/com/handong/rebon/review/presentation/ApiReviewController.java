package com.handong.rebon.review.presentation;

import java.net.URI;
import java.util.List;

import com.handong.rebon.auth.domain.Login;
import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.auth.domain.RequiredLogin;
import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.application.dto.request.*;
import com.handong.rebon.review.application.dto.response.ReviewEmpathizeResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByMemberResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByShopResponseDto;
import com.handong.rebon.review.presentation.dto.ReviewAssembler;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.review.presentation.dto.response.ReviewEmpathizeResponse;
import com.handong.rebon.review.presentation.dto.response.ReviewGetByMemberResponse;
import com.handong.rebon.review.presentation.dto.response.ReviewGetByShopResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
            @PathVariable Long shopId,
            @PageableDefault Pageable pageable
    ) {
        ReviewGetByShopRequestDto reviewGetByShopRequestDto = ReviewGetByShopRequestDto.builder()
                                                                                       .shopId(shopId)
                                                                                       .memberId(loginMember.getId())
                                                                                       .pageable(pageable)
                                                                                       .build();

        List<ReviewGetByShopResponseDto> reviews = reviewService.findAllByShop(reviewGetByShopRequestDto);

        return ResponseEntity.ok(ReviewGetByShopResponse.convert(reviews));
    }

    @RequiredLogin
    @GetMapping("/my-reviews") // TODO api 정하기
    public ResponseEntity<List<ReviewGetByMemberResponse>> getReviewsByMember(
            @Login LoginMember loginMember,
            @PageableDefault Pageable pageable
    ) {

        ReviewGetByMemberRequestDto reviewRequestDto = ReviewGetByMemberRequestDto.builder()
                                                                                  .memberId(loginMember.getId())
                                                                                  .pageable(pageable)
                                                                                  .build();
        List<ReviewGetByMemberResponseDto> reviews = reviewService.findAllByMember(reviewRequestDto);

        return ResponseEntity.ok(ReviewGetByMemberResponse.convert(reviews));
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

    @RequiredLogin
    @PostMapping("/reviews/{reviewId}/empathize")
    public ResponseEntity<Object> empathize(
            @Login LoginMember loginMember, @PathVariable Long reviewId
    ) {
        ReviewEmpathizeRequestDto empathizeRequestDto = new ReviewEmpathizeRequestDto(loginMember.getId(), reviewId);
        ReviewEmpathizeResponseDto reviewEmpathizeResponseDto = reviewService.empathize(empathizeRequestDto);
        return ResponseEntity.ok(ReviewEmpathizeResponse.from(reviewEmpathizeResponseDto));
    }

    @RequiredLogin
    @PostMapping("/reviews/{reviewId}/unempathize")
    public ResponseEntity<Object> unempathize(
            @Login LoginMember loginMember, @PathVariable Long reviewId
    ) {
        ReviewEmpathizeRequestDto empathizeRequestDto = new ReviewEmpathizeRequestDto(loginMember.getId(), reviewId);
        ReviewEmpathizeResponseDto reviewEmpathizeResponseDto = reviewService.unEmpathize(empathizeRequestDto);
        return ResponseEntity.ok(ReviewEmpathizeResponse.from(reviewEmpathizeResponseDto));
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> update(
            @Login LoginMember loginMember,
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest reviewRequest
    ) {
        ReviewUpdateRequestDto reviewUpdateRequestDto = ReviewAssembler.reviewUpdateRequestDto(loginMember.getId(), reviewId, reviewRequest);
        reviewService.update(reviewUpdateRequestDto);

        return ResponseEntity.ok()
                             .build();
    }
}
