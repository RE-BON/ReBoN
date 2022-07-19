package com.handong.rebon.member.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.*;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.review.domain.empathy.Empathy;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.like.Likes;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Empathy> empathies = new ArrayList<>();

    private boolean isAgreed;

    @Builder.Default
    private boolean isAdmin = Boolean.FALSE;

    private String oauthProvider;

    public Member(String email, String nickname, boolean isAgreed, String oauthProvider) {
        this.oauthProvider = oauthProvider;
        this.isAgreed = isAgreed;
        this.profile = new Profile(email, nickname);
    }

    public boolean isSame(Member member) {
        return this.id.equals(member.id);
    }

    public boolean isSame(Long id) {
        return this.id.equals(id);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void empathizeReview(Empathy empathy) {
        empathies.add(empathy);
    }

    public void unEmpathizeReview(Empathy empathy){
        empathies.remove(empathy);
    }

    public void update(String nickName, boolean isAgreed) {
        profile = new Profile(profile.getEmail(), nickName);
        this.isAgreed = isAgreed;
    }

    public void likeShop(Likes likes) {
        this.likes.add(likes);
    }

    public void unLike(Likes like) {
        this.likes.remove(like);
    }

    public String getNickName() {
        return profile.getNickname();
    }

    public String getEmail() { return profile.getEmail(); }

    public String getImage() { return profile.getImage(); }

    public List<Likes> filterByCategory(Category category) {
        return likes.stream()
                    .filter(like -> like.isSameCategory(category))
                    .collect(Collectors.toList());
    }
}
