package com.handong.rebon.shop.presentation;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.handong.rebon.auth.domain.Login;
import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.CategoryAssembler;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.request.ShopSearchDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.presentation.dto.request.ShopRequest;
import com.handong.rebon.shop.presentation.dto.request.ShopSearchRequest;
import com.handong.rebon.tag.application.TagService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        ShopRequest shopRequest = new ShopRequest();
        model.addAttribute("shopRequest", shopRequest);
        return "shop/createForm";
    }

    @PostMapping("/shops")
    public String registerOne(ShopRequest shopRequest) {
        Long id = shopService.create(shopRequest.toDto());
        return "redirect:/admin/shops/" + URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8);
    }

    @GetMapping("/shops/{id}")
    public String viewShop(@Login LoginMember loginMember, @PathVariable Long id, Model model) {
        ShopResponseDto shopResponseDto = shopService.findOneById(id, loginMember);
        model.addAttribute("shop", shopResponseDto);
        return "shop/detail";
    }

    @DeleteMapping("/shops/{id}")
    public String delete(@PathVariable Long id) {
        shopService.delete(id);
        return "home";
    }

    @GetMapping("/shops/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        Shop shop = shopService.findById(id);
        model.addAttribute("shop", shop);
        model.addAttribute("tags", tagService.findTags());

        List<RootCategoryResponseDto> categories = categoryService.findRootCategoriesAndChildren();
        model.addAttribute("categories", CategoryAssembler.rootCategoryResponses(categories));

        ShopRequest shopRequest = new ShopRequest();
        model.addAttribute("shopRequest", shopRequest);
        return "shop/updateForm";
    }

    @PutMapping("/shops/{id}")
    public String update(@PathVariable Long id, ShopRequest shopRequest) {
        Long updatedShopId = shopService.update(id, shopRequest.toDto());
        return "redirect:/admin/shops/" + URLEncoder.encode(String.valueOf(updatedShopId), StandardCharsets.UTF_8);
    }

    @GetMapping("/shops/condition")
    public String shopSearchCondition(Model model) {
        model.addAttribute("tags", tagService.findTags());
        List<RootCategoryResponseDto> categories = categoryService.findRootCategoriesAndChildren();
        model.addAttribute("categories", CategoryAssembler.rootCategoryResponses(categories));

        model.addAttribute("shopSearchRequest", new ShopSearchRequest());
        return "shop/searchCondition";
    }

    @GetMapping("/shops")
    public String allShops(
            @Login LoginMember loginMember,
            ShopSearchRequest request,
            @PageableDefault Pageable pageable,
            Model model
    ) {
        ShopSearchDto shopSearchDto = request.toDto(pageable, loginMember);
        List<ShopSimpleResponseDto> responses = shopService.search(shopSearchDto);
        model.addAttribute("shops", responses);
        return "shop/list";
    }
}
