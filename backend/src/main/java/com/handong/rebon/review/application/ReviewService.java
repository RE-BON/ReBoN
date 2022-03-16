package com.handong.rebon.review.application;

import com.handong.rebon.review.application.dto.request.AdminReviewCreateRequestDto;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewImage;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Long adminReviewCreate(AdminReviewCreateRequestDto adminReviewCreateRequestDto) {
        //reviewcontent 생성
        ReviewContent reviewContent = createReviewContent(adminReviewCreateRequestDto);

        //review score 생성
        ReviewScore reviewScore = new ReviewScore(5, 0);

        //이미지 저장
        ReviewImage reviewImage = new ReviewImage();

        Review review = create(reviewContent, reviewScore, reviewImage);

        reviewRepository.save(review);
        return review.getId();
    }

    private Review create(ReviewContent reviewContent, ReviewScore reviewScore, ReviewImage reviewImage) {
        return Review.builder()
                    .reviewContent(reviewContent)
                    .reviewScore(reviewScore)
                    .build();
    }

    private ReviewContent createReviewContent(AdminReviewCreateRequestDto adminReviewCreateRequestDto) {
        return new ReviewContent(
                adminReviewCreateRequestDto.getTitle(),
                adminReviewCreateRequestDto.getContent(),
                adminReviewCreateRequestDto.getTip());
    }
}
