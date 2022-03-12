package com.handong.rebon.shop.domain.content;

import javax.persistence.Embeddable;

@Embeddable
public class ShopScore {
    private double star;
    private int likeCount;
}
