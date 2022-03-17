package com.handong.rebon.menu.application;

import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.menu.presentation.dto.request.MenuGroupRequest;

import org.springframework.stereotype.Service;

@Service
public class MenuService {

    public List<Menu> createMenu(List<MenuGroupRequest> menus) {
        return new ArrayList<>();
    }
}
