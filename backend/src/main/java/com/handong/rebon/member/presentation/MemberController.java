package com.handong.rebon.member.presentation;

import java.net.URI;

import com.handong.rebon.auth.domain.RequiredLogin;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.application.dto.request.MemberUpdateRequestDto;
import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.presentation.dto.MemberAssembler;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;
import com.handong.rebon.member.presentation.dto.request.MemberUpdateRequest;
import com.handong.rebon.member.presentation.dto.response.MemberCreateResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/members/nickname/check-duplicate")
    public ResponseEntity<Void> nicknameDuplicateCheck(@RequestBody String nickname) {
        memberService.checkNicknameDuplicate(nickname);
        return ResponseEntity.ok()
                             .build();
    }

    @RequiredLogin
    @PatchMapping("/members/{memberId}")
    public ResponseEntity<Void> update(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequest memberUpdateRequest
    ) {
        MemberUpdateRequestDto memberUpdateRequestDto = MemberAssembler.memberUpdateRequestDto(memberId, memberUpdateRequest);
        memberService.update(memberUpdateRequestDto);

        return ResponseEntity.ok()
                             .build();
    }
}
