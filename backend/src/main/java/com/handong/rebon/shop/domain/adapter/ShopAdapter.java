package com.handong.rebon.shop.domain.adapter;

import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopData;
import com.handong.rebon.shop.domain.category.Category;

public interface ShopAdapter {
    boolean supports(Category category);
    Shop create(ShopData data);
}
