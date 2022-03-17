package com.handong.rebon.menu.application;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.menu.domain.MenuGroup;
import com.handong.rebon.menu.domain.repository.MenuGroupRepository;
import com.handong.rebon.menu.domain.repository.MenuRepository;
import com.handong.rebon.menu.presentation.dto.request.MenuGroupRequest;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;

    public List<Menu> createMenu(Shop shop, List<MenuGroupRequest> menuGroupRequests) {
        List<Menu> menus = menuGroupRequests.stream()
                                            .flatMap(menuGroupRequest -> {
                                                MenuGroup menuGroup = new MenuGroup(menuGroupRequest.getName());
                                                MenuGroup savedMenuGroup = menuGroupRepository.save(menuGroup);
                                                return menuGroupRequest.getMenus()
                                                                       .stream()
                                                                       .map(menuRequest -> new Menu(
                                                                               menuRequest.getName(),
                                                                               menuRequest.getPrice(),
                                                                               savedMenuGroup,
                                                                               shop)
                                                                       );
                                            })
                                            .collect(Collectors.toList());

        return menuRepository.saveAll(menus);
    }
}
