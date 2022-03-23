package com.handong.rebon.shop.domain.content;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ShopScore {
    private double star;
    private int likeCount;
}
