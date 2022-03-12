package com.handong.rebon.menu.domain;

import javax.persistence.*;

import com.handong.rebon.shop.domain.item.Shop;

@Entity
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @ManyToOne
    private MenuGroup menuGroup;

    @ManyToOne
    private Shop shop;
}
