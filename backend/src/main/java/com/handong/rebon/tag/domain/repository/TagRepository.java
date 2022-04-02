package com.handong.rebon.tag.domain.repository;

import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTags();
}
