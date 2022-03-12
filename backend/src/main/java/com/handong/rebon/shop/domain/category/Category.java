package com.handong.rebon.shop.domain.category;

import javax.persistence.*;

import com.handong.rebon.shop.domain.item.Shops;

@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Shops shops;

    @ManyToOne
    private Category parent;

    @Embedded
    private Categories children;
}
