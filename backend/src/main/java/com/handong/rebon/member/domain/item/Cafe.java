package com.handong.rebon.member.domain.item;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.handong.rebon.menu.domain.Menu;

@Entity
@DiscriminatorValue("C")
public class Cafe extends Shop {

    @OneToMany(mappedBy = "shop")
    private List<Menu> menus;
}
