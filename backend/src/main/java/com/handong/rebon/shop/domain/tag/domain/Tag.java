package com.handong.rebon.shop.domain.tag.domain;

import com.handong.rebon.exception.tag.TagNameException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<ShopTag> shopTags = new ArrayList<ShopTag>();

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
