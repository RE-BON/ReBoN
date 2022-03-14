package com.handong.rebon.shop.domain.tag;

import javax.persistence.*;

import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Tag tag;

    public ShopTag(Shop shop, Tag tag) {
        this.shop = shop;
        this.tag = tag;
    }
}
