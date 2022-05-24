package com.handong.rebon.shop.domain.repository;

import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopSearchCondition;
import com.handong.rebon.tag.domain.Tag;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.handong.rebon.shop.domain.QShop.shop;
import static com.handong.rebon.shop.domain.category.QShopCategory.shopCategory;
import static com.handong.rebon.shop.domain.content.QShopImage.shopImage;
import static com.handong.rebon.shop.domain.tag.QShopTag.shopTag;

public class ShopRepositoryCustomImpl extends QuerydslRepositorySupport implements ShopRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ShopRepositoryCustomImpl(EntityManager em) {
        super(Shop.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Shop> searchShopByConditionApplyPage(ShopSearchCondition condition, Pageable pageable) {
        JPAQuery<Shop> query = queryFactory.select(shop)
                                           .from(shop)
                                           .where(categoryEq(condition.getCategory())
                                                   .and(tagEq(condition.getTag()))
                                                   .and(containsSubCategories(condition.getSubs()))
                                                   .and(isMainImage())
                                                   .and(isOpen(condition.isOpen())))
                                           .leftJoin(shop.shopTags, shopTag)
                                           .leftJoin(shop.shopCategories, shopCategory)
                                           .leftJoin(shop.shopImages.shopImages, shopImage)
                                           .distinct();

        JPQLQuery<Shop> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Shop> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    private BooleanExpression isOpen(boolean open) {
        if (!open) {
            return null;
        }
        LocalTime now = LocalTime.now();
        return shop.shopContent.start.loe(now)
                                     .and(shop.shopContent.end.goe(now));
    }

    private BooleanExpression categoryEq(Category category) {
        return shop.category.eq(category);
    }

    private BooleanExpression tagEq(Tag tag) {
        return shop.shopTags.any().tag.eq(tag);
    }

    private BooleanExpression containsSubCategories(List<Category> subs) {
        if (subs.isEmpty()) {
            return null;
        }
        return shop.shopCategories.any().category.in(subs);
    }

    private BooleanExpression isMainImage() {
        return shop.shopImages.shopImages.any().isMain.isTrue();
    }
}
