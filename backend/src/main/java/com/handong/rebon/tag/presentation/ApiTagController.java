package com.handong.rebon.tag.presentation;

import com.handong.rebon.tag.application.TagService;

import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.presentation.dto.TagAssembler;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@Controller
public class ApiTagController {

    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<TagResponse>> getTags() {
        List<TagResponseDto> tagDtos = tagService.findTags();
        List<TagResponse> responses = TagAssembler.rootCategoryResponses(tagDtos);
        return ResponseEntity.ok(responses);
    }
}
