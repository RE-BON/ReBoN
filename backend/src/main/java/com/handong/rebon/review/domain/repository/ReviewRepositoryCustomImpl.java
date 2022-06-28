package com.handong.rebon.review.domain.repository;

import javax.persistence.EntityManager;

import com.handong.rebon.review.domain.Review;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.handong.rebon.review.domain.QReview.review;

public class ReviewRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryCustomImpl(EntityManager em) {
        super(Review.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Review> searchReviewByKeywordApplyPage(String keyword, Pageable pageable) {
        JPAQuery<Review> query = queryFactory.select(review)
                                             .from(review)
                                             .where((review.reviewContent.content.like(keyword))
                                                     .or(review.reviewContent.tip.like(keyword))
                                             );


        JPQLQuery<Review> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Review> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
