package com.handong.rebon.acceptance;

import io.restassured.specification.RequestSpecification;

public class AcceptanceUtils {
    private static RequestSpecification requestSpecification;

    private AcceptanceUtils() {
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public static void setRequestSpecification(RequestSpecification requestSpecification) {
        AcceptanceUtils.requestSpecification = requestSpecification;
    }
}
