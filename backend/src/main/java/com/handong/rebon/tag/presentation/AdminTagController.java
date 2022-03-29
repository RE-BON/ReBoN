package com.handong.rebon.tag.presentation;

import com.handong.rebon.tag.application.TagService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin/tags")
@Controller
public class AdminTagController {

    private final TagService tagService;
}
