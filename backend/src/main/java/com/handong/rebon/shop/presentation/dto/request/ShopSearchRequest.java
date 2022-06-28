package com.handong.rebon.shop.presentation.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.auth.domain.LoginMember;
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
    private boolean open;
    private List<Long> subCategories = new ArrayList<>();

    public ShopSearchDto toDto(Pageable pageable, LoginMember loginMember) {
        return ShopSearchDto.builder()
                            .tag(tag)
                            .category(category)
                            .isOpen(open)
                            .loginMember(loginMember)
                            .subCategories(subCategories)
                            .pageable(pageable)
                            .build();
    }
}
