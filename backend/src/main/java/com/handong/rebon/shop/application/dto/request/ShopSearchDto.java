package com.handong.rebon.shop.application.dto.request;

import java.util.List;

import com.handong.rebon.auth.domain.LoginMember;

import org.springframework.data.domain.Pageable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopSearchDto {
    private Long tag;
    private Long category;
    private boolean isOpen;
    private LoginMember loginMember;
    private List<Long> subCategories;
    private Pageable pageable;

    @Builder
    public ShopSearchDto(Long tag, Long category, boolean isOpen, LoginMember loginMember, List<Long> subCategories, Pageable pageable) {
        this.tag = tag;
        this.category = category;
        this.isOpen = isOpen;
        this.loginMember = loginMember;
        this.subCategories = subCategories;
        this.pageable = pageable;
    }
}
