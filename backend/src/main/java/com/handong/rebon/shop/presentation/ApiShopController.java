package com.handong.rebon.shop.presentation;

import java.util.List;

import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.request.ShopSearchDto;
import com.handong.rebon.shop.presentation.dto.request.ShopSearchRequest;
import com.handong.rebon.shop.presentation.dto.response.ShopSimpleResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ApiShopController {
    private final ShopService shopService;

    @GetMapping("/shops")
    public ResponseEntity<List<ShopSimpleResponse>> searchShops(
            @ModelAttribute ShopSearchRequest condition,
            @PageableDefault Pageable pageable
    ) {
        ShopSearchDto shopSearchDto = condition.toDto(pageable);
        List<ShopSimpleResponse> responses = shopService.search(shopSearchDto);
        return ResponseEntity.ok(responses);
    }
}
