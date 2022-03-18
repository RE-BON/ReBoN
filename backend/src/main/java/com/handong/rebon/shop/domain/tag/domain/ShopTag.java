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

//    public void setShop(Shop shop){
//        this.shop = shop;
//        shop.getShops().add(this);
//    }
//
//    public void setTag(Tag tag){
//        this.tag = tag;
//        tag.getTags().add(this);
//    }
}
