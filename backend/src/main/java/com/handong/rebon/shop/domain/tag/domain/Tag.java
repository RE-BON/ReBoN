package com.handong.rebon.shop.domain.tag.domain;

import com.handong.rebon.exception.tag.TagNameException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

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

    @Builder
    public Tag(String name) {
        if (isNotValidTag(name)) {
            throw new TagNameException();
        }
        this.name = name;
    }

    private boolean isNotValidTag(String name) {
        return Objects.isNull(name) || name.isBlank();
    }
}
