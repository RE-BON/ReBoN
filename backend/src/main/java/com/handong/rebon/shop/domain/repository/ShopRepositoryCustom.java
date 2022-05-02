package com.handong.rebon.shop.domain.repository;

import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopSearchCondition;

import com.handong.rebon.tag.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopRepositoryCustom {
    Page<Shop> searchShopByConditionApplyPage(ShopSearchCondition condition, Pageable pageable);
}
