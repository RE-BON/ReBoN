package com.handong.rebon.tag.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.exception.tag.TagNameException;
import com.handong.rebon.shop.domain.tag.ShopTag;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Document(indexName = "tags")
@Setting(settingPath = "/elasticsearch/settings/settings.json")
@Mapping(mappingPath = "/elasticsearch/mappings/mappings.json")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "deleted = false")
public class Tag extends BaseEntity {

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

    public void delete() {
        deleteShopTag();
        this.deleteContent();
    }

    public void deleteShopTag() {
        this.shopTags.forEach(ShopTag::delete);
    }
}
