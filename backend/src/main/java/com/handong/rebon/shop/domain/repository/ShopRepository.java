package com.handong.rebon.shop.domain.repository;

import java.util.List;

import com.handong.rebon.category.Category;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByCategory(Category category);
}
