package com.handong.rebon.shop.infrastructure.dto;

public class ErrorDto {
    private String code;
    private String msg;
    private String displayMsg;
    private String extraInfo;

    public ErrorDto() {
    }

    public ErrorDto(String code, String msg, String displayMsg, String extraInfo) {
        this.code = code;
        this.msg = msg;
        this.displayMsg = displayMsg;
        this.extraInfo = extraInfo;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDisplayMsg() {
        return displayMsg;
    }

    public String getExtraInfo() {
        return extraInfo;
    }
}
