package com.handong.rebon.menu.domain.repository;

import com.handong.rebon.menu.domain.MenuGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {
}
