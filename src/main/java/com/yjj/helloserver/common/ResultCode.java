package com.yjj.helloserver.common;

public enum ResultCode {
    USER_NOT_EXIST(404,"用户不存在"),
    USER_EXIST(501,"用户已存在"),
    PASSWORD_ERROR(401,"密码错误");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}