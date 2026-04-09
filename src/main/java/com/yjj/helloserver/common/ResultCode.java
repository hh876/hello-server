package com.yjj.helloserver.common;

public enum ResultCode {
    USER_EXIST(501, "用户名已存在"),
    USER_NOT_EXIST(502, "用户不存在"),
    PASSWORD_ERROR(503, "密码错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}