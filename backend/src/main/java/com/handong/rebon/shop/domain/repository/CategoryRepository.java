package com.handong.rebon.shop.domain.repository;

import com.handong.rebon.shop.domain.category.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

}
