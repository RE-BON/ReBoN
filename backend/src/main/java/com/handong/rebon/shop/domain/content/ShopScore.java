package com.handong.rebon.shop.domain.content;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ShopScore {
    private double star;
    private int likeCount;
}
