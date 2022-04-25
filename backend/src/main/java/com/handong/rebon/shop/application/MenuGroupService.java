package com.handong.rebon.shop.application;

import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.menu.MenuGroup;
import com.handong.rebon.shop.domain.repository.MenuGroupRepository;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MenuGroupService {
    private final MenuGroupRepository menuGroupRepository;

    public List<Menu> createMenu(Shop shop, List<MenuGroupRequestDto> menuGroupRequests) {
        List<Menu> menus = new ArrayList<>();
        for (MenuGroupRequestDto menuGroupRequest : menuGroupRequests) {
            MenuGroup menuGroup = new MenuGroup(menuGroupRequest.getName());
            menuGroup.belongTo(shop);

            createMenuBelongToMenuGroup(menus, menuGroupRequest, menuGroup);

            menuGroupRepository.save(menuGroup);
        }
        return menus;
    }

    private void createMenuBelongToMenuGroup(List<Menu> menus, MenuGroupRequestDto menuGroupRequest, MenuGroup menuGroup) {
        for (MenuRequestDto menuRequest : menuGroupRequest.getMenus()) {
            Menu menu = new Menu(menuRequest.getName(), menuRequest.getPrice());
            menus.add(menu);
            menuGroup.addMenu(menu);
        }
    }

    public void update(Shop shop, List<MenuGroupRequestDto> newMenus) {
        List<Menu> menus = new ArrayList<>();
        for (MenuGroupRequestDto menuGroupRequest : newMenus) {
            MenuGroup menuGroup = new MenuGroup(menuGroupRequest.getName());
            menuGroup.belongTo(shop);

            createMenuBelongToMenuGroup(menus, menuGroupRequest, menuGroup);

            menuGroupRepository.save(menuGroup);
        }

//        restaurant.updateMenu(menus);
    }
}
