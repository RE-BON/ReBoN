package com.handong.rebon.shop.application;

import com.handong.rebon.shop.domain.item.Shop;
import com.handong.rebon.shop.domain.repository.ShopRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopRepository shopRepository;

    //Review 메서드 작동 확인을 하기 위해 만들어 놓은 임시 메서드
    public Shop findById(Long id) {
        return shopRepository.findById(id).get();
    }
}
