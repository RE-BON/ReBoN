package com.handong.rebon.menu.domain;

import javax.persistence.*;

import com.handong.rebon.shop.domain.Shop;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @ManyToOne
    private MenuGroup menuGroup;

    @ManyToOne
    private Shop shop;

    public Menu(String name, int price, MenuGroup menuGroup, Shop shop) {
        this.name = name;
        this.price = price;
        this.menuGroup = menuGroup;
        this.shop = shop;
    }
}
