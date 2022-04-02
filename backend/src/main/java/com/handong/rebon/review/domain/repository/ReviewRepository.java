package com.handong.rebon.review.domain.repository;

import java.util.Optional;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    Page<Review> findAllByIsDeletedFalse(Pageable pageable);

    Page<Review> findAllByMemberAndIsDeletedFalse(Member member, Pageable pageable);

    Page<Review> findAllByShopAndIsDeletedFalse(Shop shop, Pageable pageable);

    Optional<Review> findByIdAndIsDeletedFalse(Long id);
}
