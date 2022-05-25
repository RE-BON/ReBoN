package com.handong.rebon.category.domain.repository;

import javax.persistence.EntityManager;

import com.handong.rebon.category.domain.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.handong.rebon.category.domain.QCategory.category;

public class CategoryRepositoryCustomImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryCustomImpl(EntityManager em) {
        super(Category.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public boolean existsByParentAndName(Long parentId, String name) {
        return queryFactory.select(category)
                           .from(category)
                           .where(category.parent.id.eq(parentId)
                                                    .and(category.name.eq(name)))
                           .fetchFirst() != null;
    }
}
