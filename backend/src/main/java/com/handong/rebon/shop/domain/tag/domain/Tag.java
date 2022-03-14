package com.handong.rebon.shop.domain.tag.domain;

import java.util.List;
import javax.persistence.*;

@Entity
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tag")
    private List<ShopTag> shopTags;
}
