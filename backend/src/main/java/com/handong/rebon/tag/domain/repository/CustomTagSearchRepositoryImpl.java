package com.handong.rebon.tag.domain.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomTagSearchRepositoryImpl implements CustomTagSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Tag> searchByKeyword(String keyword, Pageable pageable) {
        Criteria criteria = Criteria.where("name").contains(keyword);
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<Tag> search = elasticsearchOperations.search(query, Tag.class);
        return search.stream()
                     .map(SearchHit::getContent)
                     .collect(Collectors.toList());
    }
}
