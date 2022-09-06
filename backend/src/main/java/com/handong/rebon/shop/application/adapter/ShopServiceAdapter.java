package com.handong.rebon.shop.application.adapter;

import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.infrastructure.dto.ShopInfoDto;

public interface ShopServiceAdapter {
    boolean supports(Category category);

    Shop create(ShopImages shopImages, ShopRequestDto shopRequestDto);

    ShopResponseDto convertToShopResponseDto(Shop shop, LoginMember loginMember);

    void update(Shop shop, ShopRequestDto shopRequestDto);

    Shop createNaverShop(ShopImages shopImages, ShopInfoDto shopInfoDto);
}
