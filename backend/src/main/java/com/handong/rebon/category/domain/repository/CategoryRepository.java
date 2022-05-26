package com.handong.rebon.category.domain.repository;

import java.util.List;
import java.util.Optional;

import com.handong.rebon.category.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    @Query("select c from Category c where c.parent is null")
    List<Category> findRootCategories();

    @Query("select c from Category  c " +
            "join fetch c.children " +
            "where c.id = :categoryId ")
    Optional<Category> findCategoryWithChildren(@Param("categoryId") Long categoryId);

    boolean existsByParentAndName(Category category, String name);
}
