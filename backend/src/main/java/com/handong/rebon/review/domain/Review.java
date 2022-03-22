package com.handong.rebon.review.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewImage;
import com.handong.rebon.review.domain.content.ReviewImages;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.item.Shop;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Embedded
    private ReviewContent reviewContent;

    @Embedded
    private ReviewImages reviewImages;

    @Embedded
    private ReviewScore reviewScore;

    @OneToMany(mappedBy = "review")
    private List<Empathy> empathies = new ArrayList<>();

    public String getTitle() {
        return reviewContent.getTitle();
    }

    public String getContent() {
        return reviewContent.getContent();
    }

    public String getTip() {
        return reviewContent.getTip();
    }

    public double getStar() {
        return reviewScore.getStar();
    }

    public int getEmpathyCount() {
        return reviewScore.getEmpathyCount();
    }

    public String getAuthorName() {
        return member.getNickName();
    }

    public String getShopName() {
        return shop.getShopName();
    }

    public void addReviewImage(ReviewImages reviewImages) {
        this.reviewImages = reviewImages;
        this.reviewImages.addReviewImages(this);
    }
}
