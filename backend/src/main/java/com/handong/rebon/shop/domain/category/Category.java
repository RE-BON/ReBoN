package com.handong.rebon.shop.domain.category;

import javax.persistence.*;

import com.handong.rebon.shop.domain.Shops;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Shops shops;

    @ManyToOne
    private Category parent;

    @Embedded
    private Categories children;

    public Category(String name) {
        this.name = name;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
