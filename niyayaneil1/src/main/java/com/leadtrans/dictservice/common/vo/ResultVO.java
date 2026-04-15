package com.leadtrans.dictservice.common.vo;

import com.leadtrans.dictservice.common.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Setter
@Getter
@Accessors(chain = true) // Lombok链式调用
public class ResultVO<T> implements Serializable {
    private int code;
    private String msg;
    private T data;
    private long timestamp = System.currentTimeMillis();

    // 成功静态方法
    public static <T> ResultVO<T> success() {
        return success(null);
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>()
                .setCode(ResultCode.SUCCESS.code())
                .setMsg(ResultCode.SUCCESS.msg())
                .setData(data);
    }

    // 失败静态方法
    public static <T> ResultVO<T> fail(ResultCode resultCode) {
        return fail(resultCode, resultCode.msg());
    }

    public static <T> ResultVO<T> fail(ResultCode resultCode, String customMsg) {
        return new ResultVO<T>()
                .setCode(resultCode.code())
                .setMsg(customMsg);
    }

    // 业务异常快捷方法
    public static <T> ResultVO<T> businessFail(String customMsg) {
        return fail(ResultCode.INTERNAL_ERROR, customMsg);
    }
}