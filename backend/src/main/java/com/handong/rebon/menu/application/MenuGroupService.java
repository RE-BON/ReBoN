package com.handong.rebon.menu.application;

import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.menu.domain.MenuGroup;
import com.handong.rebon.menu.domain.repository.MenuGroupRepository;
import com.handong.rebon.menu.presentation.dto.request.MenuGroupRequest;
import com.handong.rebon.menu.presentation.dto.request.MenuRequest;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MenuGroupService {
    private final MenuGroupRepository menuGroupRepository;

    public List<Menu> createMenu(Shop shop, List<MenuGroupRequest> menuGroupRequests) {
        List<Menu> menus = new ArrayList<>();
        for (MenuGroupRequest menuGroupRequest : menuGroupRequests) {
            MenuGroup menuGroup = new MenuGroup(menuGroupRequest.getName());
            menuGroup.belongTo(shop);

            createMenuBelongToMenuGroup(menus, menuGroupRequest, menuGroup);

            menuGroupRepository.save(menuGroup);
        }
        return menus;
    }

    private void createMenuBelongToMenuGroup(List<Menu> menus, MenuGroupRequest menuGroupRequest, MenuGroup menuGroup) {
        for (MenuRequest menuRequest : menuGroupRequest.getMenus()) {
            Menu menu = new Menu(menuRequest.getName(), menuRequest.getPrice());
            menus.add(menu);
            menuGroup.addMenu(menu);
        }
    }
}
