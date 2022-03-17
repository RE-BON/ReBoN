package com.handong.rebon.shop.domain.tag;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TagService {

    // 태그 담당이 아니라 Shop 메서드가 잘 돌아가는지만 확인하기 위해 임시로 만든것.
    // 태그 파트랑 머지할 때 삭제하면 될듯
    public List<Tag> findAll(List<Long> tags) {
        return Arrays.asList(
                new Tag("포항"),
                new Tag("영일대")
        );
    }
}
