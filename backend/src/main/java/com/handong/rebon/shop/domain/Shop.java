package com.handong.rebon.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.like.Like;
import com.handong.rebon.shop.domain.tag.ShopTag;
import com.handong.rebon.shop.domain.tag.Tag;

import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Builder.Default
    @OneToMany(mappedBy = "shop")
    private List<Like> likes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "shop")
    private List<ShopTag> shopTags = new ArrayList<>();

    public void addTags(List<Tag> tags) {
        List<ShopTag> shopTags = tags.stream()
                                    .map(tag -> new ShopTag(this, tag))
                                    .collect(Collectors.toList());
        this.shopTags.addAll(shopTags);
    }
}
