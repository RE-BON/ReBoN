package com.handong.rebon.shop.infrastructure.dto;

import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.util.StringUtil;

import static com.handong.rebon.common.DataInitializer.HOUR_PATTERN;

public class ShopInfoDto {
    private Long id;
    private String name;
    private String tel;
    private List<String> category;
    private String roadAddress;
    private String thumUrl;
    private String bizhourInfo;
    private int menuExist;
    private String menuInfo;
    private Category mainCategory;

    public ShopInfoDto() {
    }

    public ShopInfoDto(
            Long id,
            String name,
            String tel,
            List<String> category,
            String roadAddress,
            String thumUrl,
            String bizhourInfo,
            int menuExist,
            String menuInfo
    ) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.category = category;
        this.roadAddress = roadAddress;
        this.thumUrl = thumUrl;
        this.bizhourInfo = bizhourInfo;
        this.menuExist = menuExist;
        this.menuInfo = menuInfo;
    }

    public LocalTime[] getBizhours() {
        Matcher matcher = HOUR_PATTERN.matcher(bizhourInfo);
        if (matcher.find()) {
            String time = matcher.group();
            return StringUtil.getTime(time);
        }
        return new LocalTime[]{LocalTime.MAX, LocalTime.MAX};
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public List<String> getCategory() {
        return category;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public String getThumUrl() {
        return thumUrl;
    }

    public String getBizhourInfo() {
        return bizhourInfo;
    }

    public int getMenuExist() {
        return menuExist;
    }

    public String getMenuInfo() {
        return menuInfo;
    }

    public void setMainCategory(Category category) {
        this.mainCategory = category;
    }

    public Category getMainCategory() {
        return mainCategory;
    }
}
