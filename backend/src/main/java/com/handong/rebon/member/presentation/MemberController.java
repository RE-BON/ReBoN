package com.handong.rebon.member.presentation;

import java.net.URI;

import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.presentation.dto.MemberAssembler;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;
import com.handong.rebon.member.presentation.dto.response.MemberCreateResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberCreateResponse> create(@RequestBody MemberCreateRequest memberCreateRequest) {

        MemberCreateResponseDto response = memberService.save(MemberAssembler.toMemberCreateDto(memberCreateRequest));
        URI location = URI.create("/api/members/" + response.getMemberId());

        return ResponseEntity.created(location)
                             .body(MemberCreateResponse.from(response));
    }
}
