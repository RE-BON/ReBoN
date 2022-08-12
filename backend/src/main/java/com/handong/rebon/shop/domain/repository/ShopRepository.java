package com.handong.rebon.shop.domain.repository;

import java.util.List;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {
    List<Shop> findByCategory(Category category);

    Page<Shop> findByCategoryAndLikesMember(Category category, Member member, Pageable pageable);
}
