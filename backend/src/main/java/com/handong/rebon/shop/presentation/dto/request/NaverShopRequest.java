package com.handong.rebon.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverShopRequest {
    private String keyword;
    private int displayCount;
    private int page;
}
