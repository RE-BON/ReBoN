package com.handong.rebon.shop.domain.tag;

import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.tag.domain.Tag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE shop_tag SET deleted = true WHERE id = ?")
public class ShopTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    public ShopTag(Shop shop, Tag tag) {
        this.shop = shop;
        this.tag = tag;
    }
}
