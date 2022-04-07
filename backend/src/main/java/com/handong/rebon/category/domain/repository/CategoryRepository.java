package com.handong.rebon.category.domain.repository;

import java.util.List;

import com.handong.rebon.category.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    // temp
    @Query("select c from Category c join fetch c.children where c.parent is null")
    List<Category> findRootCategories();

    // temp
    @Query("select c from Category c join fetch c.children where c.parent is not null")
    List<Category> findAllSub();
}
