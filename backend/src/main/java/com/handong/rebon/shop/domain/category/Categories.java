package com.handong.rebon.shop.domain.category;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class Categories {

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> categories;
}
