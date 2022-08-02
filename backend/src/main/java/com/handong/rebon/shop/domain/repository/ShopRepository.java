package com.handong.rebon.shop.domain.repository;

import java.util.List;
import java.util.Optional;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {
    List<Shop> findByCategory(Category category);

    Optional<Shop> findByNaverId(Long naverId);
}
