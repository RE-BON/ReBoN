package com.handong.rebon.tag.domain.repository;

import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TagSearchRepository extends ElasticsearchRepository<Tag, Long>, CustomTagSearchRepository {
}
