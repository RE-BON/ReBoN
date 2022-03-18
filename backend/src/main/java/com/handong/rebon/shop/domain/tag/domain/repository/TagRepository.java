package com.handong.rebon.shop.domain.tag.domain.repository;

import com.handong.rebon.shop.domain.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long>{
    Optional<Tag> findByName(String name);
}