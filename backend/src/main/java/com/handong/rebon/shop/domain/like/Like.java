package com.handong.rebon.shop.domain.like;

import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.shop.domain.Shop;

@Entity
@Table(name = "LIKES")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Shop shop;
}
