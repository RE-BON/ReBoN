package com.handong.rebon.review.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.exception.member.MemberForbiddenException;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewImages;
import com.handong.rebon.review.domain.content.ReviewScore;
import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.Shop;

import lombok.*;
import org.hibernate.annotations.Where;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "deleted = false")
@Entity
public class Review extends BaseEntity {

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

    @Builder.Default
    @OneToMany(mappedBy = "review")
    private List<Empathy> empathies = new ArrayList<>();

    public void addReviewImages(ReviewImages reviewImages) {
        this.reviewImages = reviewImages;
        this.reviewImages.connectReviewToReviewImage(this);
    }

    public void delete(Member member) {
        validatesAuthority(member);
        deleteContent();
    }

    private void validatesAuthority(Member member) {
        if (!(member.isAdmin() || this.member.isSame(member))) {
            throw new MemberForbiddenException();
        }
    }

    public boolean isMemberLiked(Member member) {
        return empathies.stream()
                        .anyMatch(empathy -> empathy.isSameMember(member));
    }

    public void addEmpathy(Empathy empathy) {
        empathies.add(empathy);
        empathy.belongTo(this);
    }

    public void update(Member member, String content, String tip, int star, ReviewImages reviewImages) {
        if (reviewImages != null) {
            addReviewImages(reviewImages);
        }
        validatesAuthority(member);
        reviewContent = new ReviewContent(content, tip);
        reviewScore = new ReviewScore(star, reviewScore.getEmpathyCount());
    }

    public String getContent() {
        return reviewContent.getContent();
    }

    public String getAuthorName() {
        return member.getNickName();
    }

    public String getShopName() {
        return shop.getName();
    }

    public String getTip() {
        return reviewContent.getTip();
    }

    public int getStar() {
        return reviewScore.getStar();
    }

    public int getEmpathyCount() {
        return reviewScore.getEmpathyCount();
    }

    public List<String> getImageUrls() {
        return reviewImages.getUrls();
    }

}
