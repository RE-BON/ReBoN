package com.handong.rebon.unit.member.domain;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.domain.Profile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTest {

    @Test
    @DisplayName("멤버가 다르면, false를 반환한다.")
    void isNotSameMember() {
        //given
        Member peace = creatMember(1L,"peace");
        Member james = creatMember(2L, "james");

        //when
        boolean isSameMember = peace.isSame(james);

        //then
        assertThat(isSameMember).isEqualTo(false);
    }

    @Test
    @DisplayName("멤버가 같으면, true를 반환한다.")
    void SameMember() {
        //given
        Member peace = creatMember(1L,"peace");

        //when
        boolean isSameMember = peace.isSame(peace);

        //then
        assertThat(isSameMember).isEqualTo(true);
    }

    private Member creatMember(Long id, String nickname) {
        return Member.builder()
                     .id(id)
                     .profile(new Profile(nickname))
                     .build();
    }
}
