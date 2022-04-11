package com.handong.rebon.category.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import lombok.Getter;

@Embeddable
@Getter
public class Categories {

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Category> categories = new ArrayList<>();

    public void addChild(Category category) {
        checkDuplicateCategory(category);
        this.categories.add(category);
    }

    private void checkDuplicateCategory(Category category) {
        this.categories.forEach(child -> child.validateSame(category));
    }

    public void deleteChild(Category category){
        this.categories.remove(category);
    }
}
