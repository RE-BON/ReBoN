package com.handong.rebon.shop.domain;

import java.util.List;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.tag.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopData {
    private Category category;
    private ShopContent shopContent;
    private Location location;
    private ShopImages shopImages;
    private List<Tag> tags;
    private List<Menu> menus;
}
