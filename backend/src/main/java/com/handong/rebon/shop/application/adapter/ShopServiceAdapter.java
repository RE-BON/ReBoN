package com.handong.rebon.shop.application.adapter;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImages;

public interface ShopServiceAdapter {
    boolean supports(Category category);

    Shop create(ShopImages shopImages, ShopCreateRequestDto shopCreateRequestDto);

    ShopResponseDto convertToShopResponseDto(Shop shop);
}
