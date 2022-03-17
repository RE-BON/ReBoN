package com.handong.rebon.shop.domain.tag.domain;

import javax.persistence.*;

import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    public ShopTag(Shop shop, Tag tag) {
        this.shop = shop;
        this.tag = tag;
    }
}
