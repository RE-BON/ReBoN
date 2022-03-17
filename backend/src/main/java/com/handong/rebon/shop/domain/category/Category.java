package com.handong.rebon.shop.domain.category;

import javax.persistence.*;

import com.handong.rebon.shop.domain.item.Shops;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ShopCategory> shopCategories;

    @ManyToOne
    private Category parent;

    @Embedded
    private Categories children;
}
