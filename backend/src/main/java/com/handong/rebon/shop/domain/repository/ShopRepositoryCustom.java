package com.handong.rebon.shop.domain.repository;

import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopSearchCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopRepositoryCustom {
    Page<Shop> searchShopByConditionApplyPage(ShopSearchCondition condition, Pageable pageable);

}
