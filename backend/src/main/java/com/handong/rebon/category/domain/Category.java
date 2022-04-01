package com.handong.rebon.category.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNameException;
import com.handong.rebon.shop.domain.category.ShopCategory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ShopCategory> shopCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @Embedded
    private Categories children;

    public Category(String name) {
        this(name, null);
    }

    public Category(String name, Category parent) {
        this.validatesBlankName(name);
        this.name = name;
        this.parent = parent;
        this.children = new Categories();
    }

    private void validatesBlankName(String name) {
        if (name.isBlank()) {
            throw new CategoryNameException();
        }
    }

    public void addChildCategory(Category category) {
        this.children.addChild(category);
        category.parent = this;
    }

    public void validateSame(Category category) {
        if (this.isSameName(category.name)) {
            throw new CategoryExistException();
        }
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
