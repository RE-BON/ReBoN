package com.handong.rebon.shop.domain.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    // 카테고리 담당이 아니라 Shop 메서드가 잘 돌아가는지만 확인하기 위해 임시로 만든것.
    // 카테고리 파트랑 머지할 때 삭제하면 될듯
    public Category findById(Long categoryId) {
        return new Category("식당");
    }
}
