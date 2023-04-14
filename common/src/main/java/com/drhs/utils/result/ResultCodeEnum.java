package com.drhs.utils.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "操作成功"),
    FAIL(201, "操作失败"),
    DATA_ERROR(202, "数据异常"),

    UNAUTHORIZED(401,"没登录或token过期"),
    FORBIDDEN(403, "没有相关权限"),

    SERVICE_ERROR(500, "服务异常"),
    ;

    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
