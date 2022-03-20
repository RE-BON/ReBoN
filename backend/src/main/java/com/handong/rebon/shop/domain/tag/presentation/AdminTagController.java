package com.handong.rebon.shop.domain.tag.presentation;

import com.handong.rebon.shop.domain.tag.application.TagService;
import com.handong.rebon.shop.domain.tag.application.dto.TagRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin/tags")
@Controller
public class AdminTagController {
    private final TagService tagService;

    @PostMapping("/create")
    public String create(TagRequestDto tagRequestDto) {
        Long id = tagService.createTag(tagRequestDto);
        return "";
    }
}
