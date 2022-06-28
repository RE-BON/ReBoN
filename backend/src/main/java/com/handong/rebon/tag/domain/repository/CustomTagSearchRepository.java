package com.handong.rebon.tag.domain.repository;

import java.util.List;

import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.domain.Pageable;

public interface CustomTagSearchRepository {

    List<Tag> searchByKeyword(String keyword, Pageable pageable);
}
