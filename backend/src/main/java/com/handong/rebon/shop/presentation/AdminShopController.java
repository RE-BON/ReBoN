package com.handong.rebon.shop.presentation;

import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.presentation.dto.request.ShopRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminShopController {

    private final ShopService shopService;

    @PostMapping("/shops")
    public String registerOne(ShopRequest shopRequest) {
        Long id = shopService.create(shopRequest.toDto());
        return "";
    }
}
