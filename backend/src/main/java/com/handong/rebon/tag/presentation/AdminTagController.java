package com.handong.rebon.tag.presentation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;
import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.presentation.dto.TagAssembler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminTagController {
    private final TagService tagService;

    @GetMapping("/tags/new")
    public String createForm() {
        return "tag/createForm";
    }

    @PostMapping("/tags")
    public String create(@RequestParam(value = "tagName") String tagName) {
        tagService.createTag(tagName);
        return "redirect:/admin/tags/view";
    }

    @GetMapping("/tags/view")
    public String view(Model model) {
        List<TagResponseDto> tagDtos = tagService.findTags();
        List<TagResponse> responses = TagAssembler.tagResponses(tagDtos);
        model.addAttribute("tags", responses);
        return "tag/viewForm";
    }

    @GetMapping("/tags/delete/{id}")
    public String delete(@PathVariable Long id) {
        tagService.delete(id);
        return "redirect:/admin/tags/view";
    }
}
