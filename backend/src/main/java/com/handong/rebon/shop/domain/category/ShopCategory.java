package com.handong.rebon.shop.domain.category;

import javax.persistence.*;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.domain.Shop;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class ShopCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public ShopCategory(Shop shop, Category category) {
        this.shop = shop;
        this.category = category;
    }
}
