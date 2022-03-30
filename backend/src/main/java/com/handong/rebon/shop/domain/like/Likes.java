package com.handong.rebon.shop.domain.like;

import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.shop.domain.Shop;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;
}
