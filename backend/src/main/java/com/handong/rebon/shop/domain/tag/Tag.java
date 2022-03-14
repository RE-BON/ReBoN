package com.handong.rebon.shop.domain.tag;

import java.util.List;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tag")
    private List<ShopTag> shopTags;

    public Tag(String name) {
        this.name = name;
    }
}
