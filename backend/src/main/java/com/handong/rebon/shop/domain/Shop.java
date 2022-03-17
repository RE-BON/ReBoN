package com.handong.rebon.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import com.handong.rebon.exception.shop.ShopTagNumberException;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.like.Like;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.tag.ShopTag;
import com.handong.rebon.shop.domain.tag.Tag;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn
public abstract class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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
    private final List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private final List<ShopTag> shopTags = new ArrayList<>();

    public Shop(
            Long id,
            Category category,
            ShopContent shopContent,
            ShopImages shopImages,
            Location location,
            ShopScore shopScore
    ) {
        this.id = id;
        this.category = category;
        this.shopContent = shopContent;
        this.shopImages = shopImages;
        this.location = location;
        this.shopScore = shopScore;
    }

    public void addTags(List<Tag> tags) {
        if (tags.isEmpty()) {
            throw new ShopTagNumberException();
        }
        List<ShopTag> shopTags = tags.stream()
                                     .map(tag -> new ShopTag(this, tag))
                                     .collect(Collectors.toList());
        this.shopTags.addAll(shopTags);
    }
}
