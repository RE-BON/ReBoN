package com.handong.rebon.review.domain.repository;

import java.util.List;

import com.handong.rebon.review.domain.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.reviewContent.content like :keyword or r.reviewContent.tip like :keyword")
    List<Review> findAllByReviewContentContaining(@Param("keyword") String keyword);

    List<Review> findAllByMemberId(Long memberId);

    List<Review> findAllByShopId(Long shopId);
}
