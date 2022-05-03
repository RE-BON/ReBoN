package com.handong.rebon.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.exception.shop.ShopTagNumberException;
import com.handong.rebon.shop.domain.category.ShopCategory;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.like.Likes;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.tag.ShopTag;
import com.handong.rebon.tag.domain.Tag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn
@SQLDelete(sql = "UPDATE shop SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public abstract class Shop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<ShopCategory> shopCategories = new ArrayList<>();

    @Embedded
    private ShopContent shopContent;

    @Embedded
    private ShopImages shopImages;

    @Embedded
    private Location location;

    @Embedded
    private ShopScore shopScore;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<ShopTag> shopTags = new ArrayList<>();

    public Shop(
            Long id,
            Category category,
            ShopContent shopContent,
            ShopImages shopImages,
            Location location,
            ShopScore shopScore,
            boolean deleted
    ) {
        super(deleted);
        this.id = id;
        this.category = category;
        this.shopContent = shopContent;
        this.shopImages = shopImages;
        this.location = location;
        this.shopScore = shopScore;

        shopImages.belongTo(this);
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

    public void addCategories(Category parent, List<Category> subCategories) {
        this.category = parent;
        List<ShopCategory> shopCategories = subCategories.stream()
                .map(category -> new ShopCategory(this, category))
                .collect(Collectors.toList());

        this.shopCategories.addAll(shopCategories);
    }

    public String getMainImage() {
        return shopImages.mainImage();
    }

    public String getName() {
        return shopContent.getName();
    }

    public double getStar() {
        return shopScore.getStar();
    }
}
