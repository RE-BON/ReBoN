package com.handong.rebon.member.domain.content;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class ShopImages {

    @OneToMany(mappedBy = "shop")
    private List<ShopImage> shopImages;
}
