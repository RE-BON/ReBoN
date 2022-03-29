package com.handong.rebon.shop.domain.content;

import javax.persistence.*;

import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShopImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private boolean isMain;

    public ShopImage(String url) {
        this(null, url, false);
    }

    public ShopImage(String url, boolean isMain) {
        this(null, url, isMain);
    }

    public ShopImage(Long id, String url, boolean isMain) {
        this.id = id;
        this.url = url;
        this.isMain = isMain;
    }

    public void belongTo(Shop shop) {
        this.shop = shop;
    }

    public boolean isMain() {
        return this.isMain;
    }
}
