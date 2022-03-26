package com.handong.rebon.tag.domain;

import com.handong.rebon.exception.tag.TagNameException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
