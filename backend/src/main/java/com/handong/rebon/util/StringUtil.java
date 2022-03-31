package com.handong.rebon.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static String makeContainingKeyword(String keyword) {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("%");
        stringbuilder.append(keyword);
        stringbuilder.append("%");
        return stringbuilder.toString();
    }
}
