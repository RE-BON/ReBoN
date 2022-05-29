package com.handong.rebon.category.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {
    private Long parentId;
    private String name;
}
