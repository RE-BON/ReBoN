package com.handong.rebon.shop.infrastructure.dto;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.util.StringUtil;

import static com.handong.rebon.common.DataInitializer.HOUR_PATTERN;

public class ShopInfoDto {
    private Long id;
    private String name;
    private String tel;
    private List<String> category;
    private List<String> shortAddress;
    private String roadAddress;
    private String abbrAddress;
    private String thumUrl;
    private String bizhourInfo;
    private int menuExist;
    private String menuInfo;
    private Category mainCategory;
    private String searchTag;

    public ShopInfoDto() {
    }

    public ShopInfoDto(
            Long id,
            String name,
            String tel,
            List<String> category,
            List<String> shortAddress,
            String roadAddress,
            String abbrAddress,
            String thumUrl,
            String bizhourInfo,
            int menuExist,
            String menuInfo,
            String searchTag
    ) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.category = category;
        this.shortAddress = shortAddress;
        this.roadAddress = roadAddress;
        this.abbrAddress = abbrAddress;
        this.thumUrl = thumUrl;
        this.bizhourInfo = bizhourInfo;
        this.menuExist = menuExist;
        this.menuInfo = menuInfo;
        this.searchTag = searchTag;
    }

    public LocalTime[] getBizhours() {
        if (Objects.isNull(bizhourInfo)) {
            return new LocalTime[]{LocalTime.MIN, LocalTime.MIN};
        }

        Matcher matcher = HOUR_PATTERN.matcher(bizhourInfo);
        if (matcher.find()) {
            String time = matcher.group();
            return StringUtil.getTime(time);
        }
        return new LocalTime[]{LocalTime.MIN, LocalTime.MIN};
    }

    public String basicAddress() {
        return shortAddress.get(0);
    }

    public String getAbbreviationAddress() {
        return abbrAddress.split(" ")[0];
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

    public List<String> getShortAddress() {
        return shortAddress;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public String getAbbrAddress() {
        return abbrAddress;
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

    public Category getMainCategory() {
        return mainCategory;
    }

    public String getSearchTag() {
        return searchTag;
    }

    public void setBasicData(Category category, String query) {
        this.mainCategory = category;
        this.searchTag = query;
    }

    public String getTagCandidates() {
        return basicAddress() + " " + getAbbreviationAddress() + " " + searchTag;
    }
}
