package com.handong.rebon.util;

public class StringUtil {
    public static String makeContainingKeyword(String keyword) {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("%");
        stringbuilder.append(keyword);
        stringbuilder.append("%");
        return stringbuilder.toString();
    }
}
