package com.handong.rebon.tag.domain;

import javax.persistence.*;

import com.handong.rebon.shop.domain.Shop;

@Entity
public class ShopTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Tag tag;
}
