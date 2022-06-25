package com.handong.rebon.review.domain.repository;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    Page<Review> findAllByMember(Member member, Pageable pageable);

    Page<Review> findAllByShop(Shop shop, Pageable pageable);

    @Query("select r from Review r " +
            "where r.reviewContent.tip is not null and r.shop.id = :shopId ")
    Page<Review> findAllByShopContainTips(Shop shop, Pageable pageable, @Param("shopId") Long shopId);
}
