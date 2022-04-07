package com.handong.rebon.shop.presentation;

import java.util.List;

import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.request.ShopSearchDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
import com.handong.rebon.shop.presentation.dto.request.ShopSearchRequest;
import com.handong.rebon.shop.presentation.dto.response.ShopResponse;
import com.handong.rebon.shop.presentation.dto.response.ShopSimpleResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<ShopSimpleResponseDto> responses = shopService.search(shopSearchDto);
        return ResponseEntity.ok(ShopSimpleResponse.convert(responses));
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<ShopResponse> searchShop(@PathVariable Long id) {
        ShopResponseDto shopResponseDto = shopService.findById(id);
        return ResponseEntity.ok(ShopResponse.from(shopResponseDto));
    }
}
