package com.handong.rebon.shop.presentation.dto.request;

import java.util.List;

import com.handong.rebon.shop.application.dto.request.ShopSearchDto;

import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopSearchRequest {
    private Long tag;
    private Long category;
    private List<Long> subCategories;

    public ShopSearchDto toDto(Pageable pageable) {
        return ShopSearchDto.builder()
                            .tag(tag)
                            .category(category)
                            .subCategories(subCategories)
                            .pageable(pageable)
                            .build();
    }
}
