package com.handong.rebon.shop.domain.like;

import java.util.Objects;
import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.shop.domain.Shop;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    public Likes(Member member, Shop shop) {
        this.member = member;
        this.shop = shop;
    }

    public boolean contain(Long id) {
        return member.isSame(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Likes))
            return false;
        Likes likes = (Likes) o;
        return Objects.equals(member, likes.member) && Objects.equals(shop, likes.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, shop);
    }
}
