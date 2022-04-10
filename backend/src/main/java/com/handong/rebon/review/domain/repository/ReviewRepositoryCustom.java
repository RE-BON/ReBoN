package com.handong.rebon.review.domain.repository;

import com.handong.rebon.review.domain.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<Review> searchReviewByKeywordApplyPage(String keyword, Pageable pageable);
}
