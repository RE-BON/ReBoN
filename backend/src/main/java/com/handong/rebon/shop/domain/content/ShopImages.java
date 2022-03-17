package com.handong.rebon.shop.domain.content;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class ShopImages {

    @OneToMany(mappedBy = "shop")
    private List<ShopImage> shopImages;

    public ShopImages(List<ShopImage> shopImages) {
        this.shopImages = shopImages;
    }
}
