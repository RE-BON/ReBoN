package com.handong.rebon.shop.domain.content;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import com.handong.rebon.shop.domain.Shop;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class ShopImages {
    public static String DEFAULT_IMAGE_URL = "default image url";

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<ShopImage> shopImages = new ArrayList<>();

    public ShopImages(List<ShopImage> shopImages) {
        this.shopImages.addAll(shopImages);
    }

    public void belongTo(Shop shop) {
        shopImages.forEach(image -> image.belongTo(shop));
    }

    public String mainImage() {
        return shopImages.stream()
                         .filter(ShopImage::isMain)
                         .map(ShopImage::getUrl)
                         .findFirst()
                         .orElseGet(() -> DEFAULT_IMAGE_URL);
    }
}
