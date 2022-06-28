package com.handong.rebon.unit.tag.domain;

import com.handong.rebon.exception.tag.TagNameException;
import com.handong.rebon.tag.domain.Tag;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TagTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("태그 이름이 빈 문자열이면 생성할 수 없다.")
    public void createBlankTag(String name) {
        //given
        //when, then
        assertThatThrownBy(() -> new Tag(name))
                .isInstanceOf(TagNameException.class);
    }
}
