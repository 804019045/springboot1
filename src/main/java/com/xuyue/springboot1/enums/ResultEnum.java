package com.xuyue.springboot1.enums;

/**
 * 用枚举管理所有异常码与其对应的异常信息
 */
public enum ResultEnum {
    VALID_ERROR(-2,"验证失败"),
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(1,"成功"),
    PRIMARY_SCHOOL(100,"你还在上小学吧"),
    MIDDLE_SCHOOL(101,"你可能在上在初中")
    ;
    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
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
