package com.handong.rebon.shop.domain;

import java.util.Collections;
import java.util.List;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.tag.domain.Tag;

import lombok.Getter;

@Getter
public class ShopSearchCondition {
    private Tag tag;
    private Category category;
    private List<Category> subs;
    private boolean isOpen;

    public ShopSearchCondition(Tag tag, Category category) {
        this(tag, category, Collections.emptyList(), true);
    }

    public ShopSearchCondition(Tag tag, Category category, List<Category> subs, boolean isOpen) {
        this.tag = tag;
        this.category = category;
        this.subs = subs;
        this.isOpen = isOpen;
    }
}
