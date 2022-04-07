package com.handong.rebon.review.domain.repository;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r " +
            "where r.reviewContent.content like :keyword or r.reviewContent.tip like :keyword")
    Page<Review> findAllByReviewContentAndTipContaining(@Param("keyword") String keyword, Pageable pageable);

    Page<Review> findAllByMember(Member member, Pageable pageable);

    Page<Review> findAllByShop(Shop shop, Pageable pageable);
}
