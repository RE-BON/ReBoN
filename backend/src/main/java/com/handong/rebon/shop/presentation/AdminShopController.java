package com.handong.rebon.shop.presentation;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.CategoryAssembler;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.presentation.dto.request.ShopRequest;
import com.handong.rebon.tag.application.TagService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminShopController {
    private final static int DEFAULT_MENU_GROUP_SIZE = 3;

    private final TagService tagService;
    private final CategoryService categoryService;
    private final ShopService shopService;

    @GetMapping("/shops/new")
    public String createForm(Model model) {
        model.addAttribute("tags", tagService.findTags());

        List<RootCategoryResponseDto> categories = categoryService.findRootCategoriesAndChildren();
        model.addAttribute("categories", CategoryAssembler.rootCategoryResponses(categories));

        ShopRequest shopRequest = new ShopRequest(DEFAULT_MENU_GROUP_SIZE);
        model.addAttribute("shopRequest", shopRequest);
        return "shop/createForm";
    }

    @PostMapping("/shops")
    public String registerOne(ShopRequest shopRequest) {
        Long id = shopService.create(shopRequest.toDto());
        return "redirect:/admin/shops/" + URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8);
    }

    @GetMapping("/shops/{id}")
    public String viewShop(@PathVariable Long id, Model model) {
        ShopResponseDto shopResponseDto = shopService.findById(id);
        model.addAttribute("shop", shopResponseDto);
        return "shop/detail";
    }
}
