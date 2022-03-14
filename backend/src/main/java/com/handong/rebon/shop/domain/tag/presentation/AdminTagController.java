package com.handong.rebon.shop.domain.tag.presentation;

import com.handong.rebon.shop.domain.tag.application.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin/tags")
@Controller
public class AdminTagController {
    private final TagService tagService;

}
