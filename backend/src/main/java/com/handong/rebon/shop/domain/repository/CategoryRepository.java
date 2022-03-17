package com.handong.rebon.shop.domain.repository;

import com.handong.rebon.shop.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.name = :user")
    Optional<Category> findByName(@Param("user") String name);

}
