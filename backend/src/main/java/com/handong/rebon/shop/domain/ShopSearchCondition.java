package com.handong.rebon.shop.domain;

import java.util.Collections;
import java.util.List;

import com.handong.rebon.category.Category;
import com.handong.rebon.tag.domain.Tag;

import lombok.Getter;

@Getter
public class ShopSearchCondition {
    private Tag tag;
    private Category category;
    private List<Category> subs;

    public ShopSearchCondition(Tag tag, Category category) {
        this(tag, category, Collections.emptyList());
    }

    public ShopSearchCondition(Tag tag, Category category, List<Category> subs) {
        this.tag = tag;
        this.category = category;
        this.subs = subs;
    }
}
