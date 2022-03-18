package com.handong.rebon.shop.domain.repository;

import com.handong.rebon.shop.domain.item.Shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
