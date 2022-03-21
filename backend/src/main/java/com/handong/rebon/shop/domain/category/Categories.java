package com.handong.rebon.shop.domain.category;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;

@Embeddable
@Getter
public class Categories {

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> categories;

    public void addChild(Category category){
        this.getCategories().add(category);
    }
}
