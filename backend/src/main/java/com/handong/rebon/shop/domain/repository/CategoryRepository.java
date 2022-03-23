package com.handong.rebon.shop.domain.repository;

import com.handong.rebon.shop.domain.category.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
