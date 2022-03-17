package com.handong.rebon.shop.domain.type;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.location.Location;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("C")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafe extends Shop {

    @OneToMany(mappedBy = "shop")
    private List<Menu> menus;

    @Builder
    public Cafe(
            Long id,
            Category category,
            ShopContent shopContent,
            ShopImages shopImages,
            Location location,
            ShopScore shopScore,
            List<Menu> menus
    ) {
        super(id, category, shopContent, shopImages, location, shopScore);
        this.menus = menus;
    }

    public void addMenu(List<Menu> menus) {

    }
}
