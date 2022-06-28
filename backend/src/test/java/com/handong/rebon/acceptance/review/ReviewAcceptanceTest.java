package com.handong.rebon.acceptance.review;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.admin.AdminCategoryRegister;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.common.admin.AdminTagRegister;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.BeforeEach;

public class ReviewAcceptanceTest extends AcceptanceTest {
    @Autowired
    private AdminTagRegister adminTagRegister;

    @Autowired
    private AdminCategoryRegister adminCategoryRegister;

    @Autowired
    private AdminShopRegister adminShopRegister;

    protected Map<String, Tag> tags = new HashMap<>();
    protected Map<String, Category> categories = new HashMap<>();
    protected Map<String, Shop> shops = new HashMap<>();

    @BeforeEach
    void setUp() {
        tags = adminTagRegister.register("포항", "한동대");
        categories = adminCategoryRegister.registerMain("식당", "숙소", "카페");
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("식당"), "분식", "양식"));
        initShops();
    }

    private void initShops() {
        shops.clear();
        initRestaurant();
    }

    private void initRestaurant() {
        shops.put("팜스발리", adminShopRegister.simpleRegister(
                "팜스발리",
                categories.get("식당"),
                Arrays.asList(categories.get("양식"), categories.get("분식")),
                Arrays.asList(tags.get("포항"), tags.get("한동대")),
                new ShopImages(Collections.singletonList(new ShopImage("url1", true)))
        ));
    }
}
