package com.handong.rebon.shop.domain.category;

import javax.persistence.*;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.shop.domain.Shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Getter
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE shop_category SET deleted = true WHERE id = ?")
public class ShopCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public ShopCategory(Shop shop, Category category) {
        this.shop = shop;
        this.category = category;
    }

    public void addCategory() {
        this.category.addShopCategory(this);
    }
}
