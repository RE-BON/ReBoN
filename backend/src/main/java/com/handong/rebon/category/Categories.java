package com.handong.rebon.category;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Categories {

    @OneToMany(mappedBy = "parent")
    private List<Category> categories;
}
