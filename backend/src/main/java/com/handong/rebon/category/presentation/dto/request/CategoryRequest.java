package com.handong.rebon.category.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private Long parentId;
    private String name;
}
