package com.handong.rebon.shop.domain;

import java.util.List;
import javax.persistence.*;

import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.like.Like;
import com.handong.rebon.shop.domain.tag.ShopTag;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Shop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    @Embedded
    private ShopContent shopContent;

    @Embedded
    private ShopImages shopImages;

    @Embedded
    private Location location;

    @Embedded
    private ShopScore shopScore;

    @OneToMany(mappedBy = "shop")
    private List<Like> likes;

    @OneToMany(mappedBy = "shop")
    private List<ShopTag> shopTags;

}
