package com.handong.rebon.shop.domain.category;

import com.handong.rebon.member.domain.domain.item.Shop;

import javax.persistence.*;

@Entity
public class ShopCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Category category;

}
