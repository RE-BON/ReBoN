package com.handong.rebon.shop.domain.menu;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "menuGroup", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    public MenuGroup(String name) {
        this.name = name;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
        menu.belongTo(this);
    }

    public void belongTo(Shop shop) {
        this.shop = shop;
    }
}
