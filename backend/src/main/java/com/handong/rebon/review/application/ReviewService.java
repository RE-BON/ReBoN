package com.handong.rebon.review.application;

import com.handong.rebon.review.application.dto.request.AdminReviewPostRequestDto;
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

    public Long adminReviewCreate(AdminReviewPostRequestDto adminReviewPostRequestDto) {
        //reviewContent 생성
        ReviewContent reviewContent = createReviewContent(adminReviewPostRequestDto);

        //review score 생성
        ReviewScore reviewScore = new ReviewScore(5, 0);
        
        Review review = createReview(reviewContent, reviewScore);

        reviewRepository.save(review);
        return review.getId();
    }

    private Review createReview(ReviewContent reviewContent, ReviewScore reviewScore) {
        return Review.builder()
                    .reviewContent(reviewContent)
                    .reviewScore(reviewScore)
                    .build();
    }

    private ReviewContent createReviewContent(AdminReviewPostRequestDto adminReviewPostRequestDto) {
        return new ReviewContent(
                adminReviewPostRequestDto.getTitle(),
                adminReviewPostRequestDto.getContent(),
                adminReviewPostRequestDto.getTip());
    }
}
