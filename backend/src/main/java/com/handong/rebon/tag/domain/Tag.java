package com.handong.rebon.tag.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.exception.tag.TagNameException;
import com.handong.rebon.shop.domain.tag.ShopTag;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(indexName = "tags")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<ShopTag> shopTags = new ArrayList<>();

    public Tag(String name) {
        this.validateBlankName(name);
        this.name = name;
    }

    private void validateBlankName(String name) {
        if (name.isBlank()) {
            throw new TagNameException();
        }
    }
}
