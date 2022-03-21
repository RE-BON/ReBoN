package com.handong.rebon.member.domain.content;

import javax.persistence.Embeddable;

@Embeddable
public class ShopScore {
    private double star;
    private int likeCount;
}
