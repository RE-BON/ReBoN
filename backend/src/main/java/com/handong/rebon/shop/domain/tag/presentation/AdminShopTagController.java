package com.handong.rebon.shop.domain.tag.presentation;

import com.handong.rebon.shop.domain.tag.application.ShopTagService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin/shoptags")
@Controller
public class AdminShopTagController {
    private final ShopTagService shopTagService;

}
