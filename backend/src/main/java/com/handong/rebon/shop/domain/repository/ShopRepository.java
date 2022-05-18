package com.handong.rebon.shop.domain.repository;

import java.util.List;

import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {

    @Query("select s from Shop s where s.category.id = :id")
    List<Shop> findShopsByCategoryId(Long id);
}
