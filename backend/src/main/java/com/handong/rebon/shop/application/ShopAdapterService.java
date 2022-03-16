package com.handong.rebon.shop.application;

import java.util.List;

import com.handong.rebon.exception.shop.NoSuchCategoryException;
import com.handong.rebon.shop.domain.adapter.ShopAdapter;
import com.handong.rebon.shop.domain.category.Category;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShopAdapterService {
    private final List<ShopAdapter> shopAdapters;

    public ShopAdapter shopAdapterByCategory(Category category) {
        return shopAdapters.stream()
                           .filter(shopAdapter -> shopAdapter.supports(category))
                           .findFirst()
                           .orElseThrow(NoSuchCategoryException::new);
    }
}
