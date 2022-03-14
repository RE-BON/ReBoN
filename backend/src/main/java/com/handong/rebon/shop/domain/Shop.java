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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Shop(Long id, Category category, ShopContent shopContent, ShopImages shopImages, Location location,
                ShopScore shopScore, List<Like> likes, List<ShopTag> shopTags) {
        this.id = id;
        this.category = category;
        this.shopContent = shopContent;
        this.shopImages = shopImages;
        this.location = location;
        this.shopScore = shopScore;
        this.likes = likes;
        this.shopTags = shopTags;
    }
}
