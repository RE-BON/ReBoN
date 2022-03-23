package com.handong.rebon.category.domain.category;

import com.handong.rebon.member.domain.item.Shop;

import javax.persistence.*;

@Entity
public class ShopCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
