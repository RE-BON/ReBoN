package com.handong.rebon.shop.domain.category;

import javax.persistence.*;

import com.handong.rebon.exception.category.CategoryNameException;

import lombok.*;

import java.util.List;

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
    private List<ShopCategory> shopCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @Embedded
    private Categories children;

    @Builder
    public Category(String name, List<ShopCategory> shopCategories, Category parent, Categories children) {
        validatesBlankName(name);
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
        this.children.checkDuplicateCategory(category);
        this.children.addChild(category);
        category.connectParent(this);
    }

    public void connectParent(Category parent) {
        this.parent = parent;
    }
}
