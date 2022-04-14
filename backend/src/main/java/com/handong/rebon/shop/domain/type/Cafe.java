package com.handong.rebon.shop.domain.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.menu.MenuGroup;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@DiscriminatorValue("C")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OnDelete(action = OnDeleteAction.CASCADE)
public class Cafe extends Shop {

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<Menu> menus = new ArrayList<>();

    @Builder
    public Cafe(
            Long id,
            Category category,
            ShopContent shopContent,
            ShopImages shopImages,
            Location location,
            ShopScore shopScore,
            boolean deleted
    ) {
        super(id, category, shopContent, shopImages, location, shopScore, deleted);
    }

    public void addMenu(List<Menu> menus) {
        this.menus.addAll(menus);
        menus.forEach(menu -> menu.belongShop(this));
    }

    public Map<MenuGroup, List<Menu>> getMenuGroupByMenuGroup() {
        return menus.stream()
                    .collect(Collectors.groupingBy(Menu::getMenuGroup));
    }
}
