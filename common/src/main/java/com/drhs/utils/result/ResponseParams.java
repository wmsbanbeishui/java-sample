package com.drhs.utils.result;

import lombok.Data;

@Data
public class ResponseParams<T> {
    private Integer code;//状态码
    private String message;//返回信息
    private T data;//数据

    //私有化
    private ResponseParams() {
    }

    //封装返回是数据
    public static <T> ResponseParams<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResponseParams<T> params = new ResponseParams<>();
        //封装数据
        if (body != null) {
            params.setData(body);
        }
        //状态码
        params.setCode(resultCodeEnum.getCode());
        //返回信息
        params.setMessage(resultCodeEnum.getMessage());
        return params;
    }

    //成功
    public static <T> ResponseParams<T> ok() {
        return build(null, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResponseParams<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    //失败
    public static <T> ResponseParams<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    public static <T> ResponseParams<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    public ResponseParams<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResponseParams<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
