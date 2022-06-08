package com.handong.rebon.tag.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.presentation.dto.TagAssembler;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@Controller
public class ApiTagController {

    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<TagResponse>> getTags() {
        List<TagResponseDto> tagDtos = tagService.findTags();
        List<TagResponse> responses = TagAssembler.tagResponses(tagDtos);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/tags/search")
    public ResponseEntity<List<TagResponse>> search(@RequestParam String keyword, Pageable pageable) {
        List<TagResponse> result = tagService.searchByKeyword(keyword, pageable)
                                             .stream()
                                             .map(TagResponse::from)
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tags/top")
    public ResponseEntity<List<TagResponse>> topTags() {
        List<TagResponseDto> tagDtos = tagService.findTopTags();
        List<TagResponse> responses = TagAssembler.tagResponses(tagDtos);
        return ResponseEntity.ok(responses);
    }
}
