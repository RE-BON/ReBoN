package com.handong.rebon.shop.domain.content;

import javax.persistence.*;

import com.handong.rebon.shop.domain.item.Shop;

@Entity
public class ShopImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    private Shop shop;
}
