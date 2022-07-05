package com.handong.rebon.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.exception.shop.ShopTagNumberException;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.shop.domain.category.ShopCategory;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.like.Likes;
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

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ShopCategory> shopCategories = new ArrayList<>();

    @Embedded
    private ShopContent shopContent;

    @Embedded
    private ShopImages shopImages;

    private String address;

    @Embedded
    private ShopScore shopScore;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ShopTag> shopTags = new ArrayList<>();

    public Shop(
            Long id,
            Category category,
            ShopContent shopContent,
            ShopImages shopImages,
            String address,
            ShopScore shopScore,
            boolean deleted
    ) {
        super(deleted);
        this.id = id;
        this.category = category;
        this.shopContent = shopContent;
        this.shopImages = shopImages;
        this.address = address;
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
        shopCategories.forEach(ShopCategory::addCategory);
        this.shopCategories.addAll(shopCategories);
    }

    public void update(ShopContent content, String address) {
        this.shopContent = content;
        this.address = address;
    }

    public void updateCategories(Category category, List<Category> subCategories) {
        this.shopCategories.clear();
        addCategories(category, subCategories);
    }

    public void updateTags(List<Tag> tags) {
        this.shopTags.clear();
        addTags(tags);
    }

    public void updateImage(ShopImages shopImages) {
        this.shopImages = shopImages;
        shopImages.belongTo(this);
    }

    public void like(Member member) {
        Likes like = new Likes(member, this);
        this.likes.add(like);
        member.likeShop(like);
    }

    public void unlike(Member member) {
        Likes like = new Likes(member, this);
        this.likes.remove(like);
        member.unLike(like);
    }

    public boolean sameCategory(Category category) {
        return this.category.equals(category);
    }

    public int getLikeCount() {
        return this.likes.size();
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

    public void validateOnlyShopTag() {
        if (this.shopTags.size() == 1) {
            throw new ShopTagNumberException();
        }
    }

    public boolean containLike(LoginMember loginMember) {
        if (loginMember.isAnonymous()) {
            return false;
        }
        return likes.stream()
                    .anyMatch(l -> l.contain(loginMember.getId()));
    }
}
