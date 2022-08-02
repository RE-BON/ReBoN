package com.handong.rebon.shop.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import com.handong.rebon.shop.domain.menu.Menu;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MenuService {
    private static String MENU_DELIMITER = "|";

    public List<Menu> createMenus(String menuInfo) {
        List<Menu> menus = new ArrayList<>();

        if (Objects.isNull(menuInfo) || menuInfo.isBlank()) {
            return menus;
        }

        StringTokenizer st = new StringTokenizer(menuInfo, MENU_DELIMITER);
        while (st.hasMoreTokens()) {
            String menu = st.nextToken();
            createMenu(menus, menu);
        }

        return menus;
    }

    private void createMenu(List<Menu> results, String menuInfo) {
        String[] menus = menuInfo.split(" ");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < menus.length - 1; i++) {
            name.append(menus[i]).append(" ");
        }
        String priceInfo = menus[menus.length - 1];
        int price = 0;
        if (priceInfo.matches("\\d{1,3}(,\\d{3})*")) {
            price = Integer.parseInt(priceInfo.replaceAll(",", ""));
        }

        Menu menu = new Menu(name.toString().strip(), price);
        results.add(menu);
    }
}
