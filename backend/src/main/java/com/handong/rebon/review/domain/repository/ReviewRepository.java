package com.handong.rebon.review.domain.repository;

import java.util.List;

import com.handong.rebon.review.domain.Review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r join fetch r.member join fetch r.shop where r.reviewContent.content like :keyword or r.reviewContent.tip like :keyword")
    List<Review> findAllByReviewContentContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("select r from Review r join fetch r.member join fetch r.shop where r.member.id = :memberId")
    List<Review> findAllByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("select r from Review r join fetch r.member join fetch r.shop where r.shop.id = :shopId")
    List<Review> findAllByShopId(@Param("shopId") Long shopId, Pageable pageable);
}
