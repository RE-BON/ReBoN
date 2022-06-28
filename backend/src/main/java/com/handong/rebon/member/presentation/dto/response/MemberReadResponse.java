package com.handong.rebon.member.presentation.dto.response;

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
    private boolean agreed;

    public static MemberReadResponse from(MemberReadResponseDto dto) {
        return MemberReadResponse.builder()
                                 .id(dto.getId())
                                 .image(dto.getImage())
                                 .email(dto.getEmail())
                                 .nickName(dto.getNickName())
                                 .agreed(dto.isAgreed())
                                 .build();
    }
}
