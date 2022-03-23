package com.handong.rebon.shop.domain.category;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import com.handong.rebon.exception.category.CategoryExistException;
import lombok.Getter;


@Embeddable
@Getter
public class Categories {

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Category> categories = new ArrayList<>();

    public void addChild(Category category) {
        this.getCategories().add(category);
    }

    public void checkDuplicateCategory(Category category) {
        this.categories.forEach(child -> checkSameName(child, category));
    }

    private void checkSameName(Category child, Category category) {
        String newCategoryName = category.getName();
        if (child.IsSameName(newCategoryName)) {
            throw new CategoryExistException();
        }
    }
}
