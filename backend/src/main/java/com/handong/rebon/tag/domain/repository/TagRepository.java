package com.handong.rebon.tag.domain.repository;

import java.util.List;

import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);

    List<Tag> findTop8ByOrderByCountDesc();
}
