package com.handong.rebon.member.presentation.dto.response;

import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.application.dto.response.MemberReadResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberReadResponse {
    private Long id;
    private String image;
    private String email;
    private String nickName;
    private boolean isAgreed;

//    public static MemberReadResponse from(MemberCreateResponseDto memberCreateResponseDto) {
//        return new MemberReadResponse(memberCreateResponseDto.getToken());
//    }

    public static MemberReadResponse from(MemberReadResponseDto dto){
        return MemberReadResponse.builder()
                .id(dto.getId())
                .image(dto.getImage())
                .email(dto.getEmail())
                .nickName(dto.getNickName())
                .isAgreed(dto.isAgreed())
                .build();
    }
}
