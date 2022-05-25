package com.handong.rebon.category.domain.repository;

public interface CategoryRepositoryCustom {
    boolean existsByParentAndName(Long parentId, String name);
}
