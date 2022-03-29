package com.handong.rebon.shop.application;

import java.util.List;

import com.handong.rebon.category.Category;
import com.handong.rebon.exception.shop.NoSuchCategoryException;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShopAdapterService {
    private final List<ShopServiceAdapter> shopServiceAdapters;

    public ShopServiceAdapter shopAdapterByCategory(Category category) {
        return shopServiceAdapters.stream()
                                  .filter(shopAdapter -> shopAdapter.supports(category))
                                  .findFirst()
                                  .orElseThrow(NoSuchCategoryException::new);
    }
}
