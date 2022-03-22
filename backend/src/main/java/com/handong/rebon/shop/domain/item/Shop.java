package com.handong.rebon.shop.domain.item;

import java.util.List;
import javax.persistence.*;

import com.handong.rebon.shop.domain.Location;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.like.Like;
import com.handong.rebon.shop.domain.tag.ShopTag;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Shop(ShopContent shopContent, ShopScore shopScore) {
        this.shopContent = shopContent;
        this.shopScore = shopScore;
    }

    public String getShopName() {
        return shopContent.getName();
    }
}
