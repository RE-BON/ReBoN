package com.handong.rebon.member.domain.item;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Shops {

    @OneToMany(mappedBy = "category")
    private List<Shop> shops;
}
