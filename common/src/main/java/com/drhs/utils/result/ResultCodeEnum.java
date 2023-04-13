package com.drhs.utils.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "操作成功"),
    FAIL(500, "操作失败"),
    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    UNAUTHORIZED(401,"没登录或token过期"),
    FORBIDDEN(403, "没有相关权限");

    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
