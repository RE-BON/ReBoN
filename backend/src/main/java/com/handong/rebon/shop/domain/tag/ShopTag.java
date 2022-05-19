package com.handong.rebon.shop.domain.tag;

import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.tag.domain.Tag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted = false")
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

    public void delete() {
        this.shop.validateOnlyShopTag();
        this.deleteContent();
    }
}
