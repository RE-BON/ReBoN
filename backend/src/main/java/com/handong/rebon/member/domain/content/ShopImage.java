package com.handong.rebon.member.domain.content;

import javax.persistence.*;

import com.handong.rebon.member.domain.item.Shop;

@Entity
public class ShopImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    private Shop shop;
}
