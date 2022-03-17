package com.handong.rebon.shop.domain.tag.domain;

import com.handong.rebon.exception.tag.TagNameException;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<ShopTag> shopTags;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Tag(String name) {
        if (isNotValidTag(name)) {
            throw new TagNameException();
        }
        this.name = name.toLowerCase();
    }

    private boolean isNotValidTag(String name) {
        return Objects.isNull(name)
                || name.isBlank();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.getName());
    }
}
