package com.handong.rebon.shop.infrastructure.dto;

import java.util.List;

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
}
