package com.handong.rebon;

import javax.persistence.EntityManager;

import com.handong.rebon.tag.domain.QTag;
import com.handong.rebon.tag.domain.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

// TODO 임시 테스트 나중에 지우기
@SpringBootTest
@Transactional
class QuerydslApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Tag tag = new Tag("한동대");
        em.persist(tag);
        JPAQueryFactory query = new JPAQueryFactory(em);
        QTag qTag = QTag.tag;
        Tag result = query
                .selectFrom(qTag)
                .fetchOne();
        Assertions.assertThat(result).isEqualTo(tag);
        Assertions.assertThat(result.getId()).isEqualTo(tag.getId());
    }
}
