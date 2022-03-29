package com.handong.rebon.shop.application.adapter;

import com.handong.rebon.category.Category;
import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImages;

public interface ShopServiceAdapter {
    boolean supports(Category category);

    Shop create(ShopImages shopImages, ShopRequestDto shopRequestDto);
}
