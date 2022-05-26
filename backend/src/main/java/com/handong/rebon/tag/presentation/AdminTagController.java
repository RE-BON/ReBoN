package com.handong.rebon.tag.presentation;
import com.handong.rebon.tag.application.TagService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminTagController {
    private final TagService tagService;

    @PostMapping("/tags/new")
    public String create(@PathVariable String tagName) {
        Long id = tagService.createTag(tagName);
        return "tag/createForm";
    }

    @DeleteMapping("/tags/delete")
    public String delete(@PathVariable Long id) {
        tagService.delete(id);
        return "tag/deleteForm";
    }
}
