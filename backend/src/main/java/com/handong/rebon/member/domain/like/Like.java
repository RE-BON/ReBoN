package com.handong.rebon.member.domain.like;

import javax.persistence.*;

import com.handong.rebon.member.Member;
import com.handong.rebon.member.domain.item.Shop;

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
