package com.handong.rebon.shop.domain.tag.domain;

import javax.persistence.*;

import com.handong.rebon.shop.domain.item.Shop;
import com.handong.rebon.shop.domain.tag.domain.Tag;

@Entity
public class ShopTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Tag tag;
}
