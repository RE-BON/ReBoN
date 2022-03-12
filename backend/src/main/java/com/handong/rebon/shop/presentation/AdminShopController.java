package com.handong.rebon.shop.presentation;

import com.handong.rebon.shop.application.ShopService;

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
    public String registerOne() {
        return "";
    }
}
