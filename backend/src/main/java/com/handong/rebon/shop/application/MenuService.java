package com.handong.rebon.shop.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.handong.rebon.shop.domain.menu.Menu;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MenuService {
    private static String MENU_DELIMITER = "|";
    private static Pattern MENU_PATTERN = Pattern.compile("([^0-9]+.[^0-9]+)([0-9]+,[0-9]+)?");

    public List<Menu> createMenus(String menuInfo) {
        List<Menu> menus = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(menuInfo, MENU_DELIMITER);
        while (st.hasMoreTokens()) {
            String menu = st.nextToken();
            createMenu(menus, menu);
        }

        return menus;
    }

    private void createMenu(List<Menu> menus, String menuInfo) {
        Matcher matcher = MENU_PATTERN.matcher(menuInfo);

        if (matcher.find()) {
            String name = matcher.group(1);
            String priceInfo = matcher.group(2);
            int price = 0;
            if (!Objects.isNull(priceInfo)) {
                price = Integer.parseInt(priceInfo.replaceAll(",", ""));
            }

            Menu menu = new Menu(name, price);
            menus.add(menu);
        }
    }
}
