package com.handong.rebon.review.domain.repository;

import com.handong.rebon.review.domain.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
