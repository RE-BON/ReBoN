package com.handong.rebon.shop.application.adapter;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.type.Lodging;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.shop.infrastructure.dto.ShopInfoDto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LodgingServiceAdapter implements ShopServiceAdapter {

    @Override
    public boolean supports(Category category) {
        return category.isSameName("숙소");
    }

    @Override
    public Shop create(ShopImages shopImages, ShopRequestDto data) {
        return Lodging.builder()
                      .shopContent(new ShopContent(data.getName(), data.getStart(), data.getEnd(), data.getPhone()))
                      .location(new Location(data.getAddress(), data.getLongitude(), data.getLatitude()))
                      .shopImages(shopImages)
                      .shopScore(new ShopScore(0.0, 0))
                      .build();
    }

    @Override
    public ShopResponseDto convertToShopResponseDto(Shop shop) {
        return ShopResponseDto.from(shop);
    }

    @Override
    public void update(Shop shop, ShopRequestDto shopRequestDto) {
    }

    @Override
    public Shop createNaverShop(ShopImages shopImages, ShopInfoDto data) {
        ShopContent content = new ShopContent(data.getName(), data.getBizhours(), data.getTel());
        Location location = new Location(data.getRoadAddress());
        ShopScore score = new ShopScore(0.0, 0);
        Lodging lodging = Lodging.builder()
                                          .shopContent(content)
                                          .location(location)
                                          .shopImages(shopImages)
                                          .shopScore(score)
                                          .naverId(data.getId())
                                          .build();
        return lodging;
    }
}
