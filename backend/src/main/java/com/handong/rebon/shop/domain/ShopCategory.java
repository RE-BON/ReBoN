package com.handong.rebon.shop.domain;

import javax.persistence.*;

import com.handong.rebon.shop.domain.category.Category;

@Entity
public class ShopCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Category category;

    public ShopCategory(Shop shop, Category category) {
        this.shop = shop;
        this.category = category;
    }
}
