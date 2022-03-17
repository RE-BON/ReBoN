package com.handong.rebon.shop.domain.content;

import javax.persistence.*;

import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShopImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    private Shop shop;

    public ShopImage(Long id, String url) {
        this.id = id;
        this.url = url;
    }
}
