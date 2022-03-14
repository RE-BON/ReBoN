package com.handong.rebon.shop.domain.item;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.domain.Shop;

@Entity
@DiscriminatorValue("R")
public class Restaurant extends Shop {

    @OneToMany(mappedBy = "shop")
    private List<Menu> menus;
}
