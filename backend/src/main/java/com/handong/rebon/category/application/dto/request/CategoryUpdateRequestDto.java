package com.handong.rebon.category.application.dto.request;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequestDto {
    private Long id;
    private Long parentId;
    private String name;

    public boolean isParentIdNull(){
        return Objects.isNull(parentId);
    }
}
