package com.handong.rebon.shop.application.dto.request;

import java.util.List;

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
    private List<Long> subCategories;
    private Pageable pageable;

    @Builder
    public ShopSearchDto(Long tag, Long category, boolean isOpen, List<Long> subCategories, Pageable pageable) {
        this.tag = tag;
        this.category = category;
        this.isOpen = isOpen;
        this.subCategories = subCategories;
        this.pageable = pageable;
    }
}
