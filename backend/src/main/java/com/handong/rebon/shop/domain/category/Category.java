package com.handong.rebon.shop.domain.category;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.shop.domain.ShopCategory;
import com.handong.rebon.shop.domain.Shops;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Shops shops;

    @ManyToOne
    private Category parent;

    @Embedded
    private Categories children;

    @OneToMany(mappedBy = "category")
    private List<ShopCategory> shopCategories = new ArrayList<>();

    public Category(String name) {
        this(null, name);
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
