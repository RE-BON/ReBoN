package com.handong.rebon.shop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.handong.rebon.shop.domain.Shop;

@Entity
@DiscriminatorValue("L")
public class Lodging extends Shop {
}
