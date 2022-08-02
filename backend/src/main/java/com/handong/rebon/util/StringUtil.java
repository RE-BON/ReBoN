package com.handong.rebon.util;

import java.time.LocalTime;
import java.util.StringTokenizer;

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

    public static LocalTime[] getTime(String businessHour) {
        StringTokenizer st = new StringTokenizer(businessHour, "~");
        String[] start = st.nextToken().split(":");
        String[] end = st.nextToken().split(":");

        if (end[0].equals("24")) {
            end[0] = "23";
            end[1] = "59";
        }

        return new LocalTime[]{
                LocalTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1])),
                LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]))
        };
    }
}
