package com.yjj.helloserver.common;

public enum ResultCode {
<<<<<<< HEAD
    USER_EXIST(501, "用户名已存在"),
    USER_NOT_EXIST(502, "用户不存在"),
    PASSWORD_ERROR(503, "密码错误");

    private int code;
=======
    SUCCESS(200, "操作成功"),
    ERROR(500, "系统异常"),
    USER_HAS_EXISTED(4001, "用户名已存在"),
    USER_NOT_EXIST(4002, "用户不存在"),
    PASSWORD_ERROR(4003, "密码错误"),
    TOKEN_INVALID(401, "未登录或token失效");

    private Integer code;
>>>>>>> origin/master
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