package com.handong.rebon.shop.domain.menu;

import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.shop.domain.Shop;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @ManyToOne
    private Shop shop;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void belongShop(Shop shop) {
        this.shop = shop;
    }
}
