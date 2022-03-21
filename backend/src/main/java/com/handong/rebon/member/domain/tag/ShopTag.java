package com.handong.rebon.member.domain.tag;

import javax.persistence.*;

import com.handong.rebon.member.domain.item.Shop;

@Entity
public class ShopTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Tag tag;
}
