package com.leadtrans.dictservice.common.exception;

import com.leadtrans.dictservice.common.enums.ResultCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 增强版业务异常（支持i18n和错误上下文）
 */
public class BizException extends RuntimeException {
    private final ResultCode code;
    private final String i18nKey;  // 国际化key
    private final Map<String, Object> context;  // 错误上下文

    public BizException(ResultCode code, String message) {
        this(code, message, null, null);
    }

    public BizException(ResultCode resultCode) {
        this(resultCode, resultCode.msg(), null, null);
    }

    public BizException(ResultCode resultCode, Map<String, Object> context) {
        this(resultCode, resultCode.msg(), null, context);
    }

    public BizException(ResultCode code, String message, String i18nKey, Map<String, Object> context) {
        super(message);
        this.code = code;
        this.i18nKey = i18nKey;
        this.context = context != null ? context : new HashMap<>();
    }

    // 添加上下文信息（链式调用）
    public BizException addContext(String key, Object value) {
        this.context.put(key, value);
        return this;
    }

    // Getters
    public ResultCode getCode() { return code; }
    public String getI18nKey() { return i18nKey; }
    public Map<String, Object> getContext() { return context; }
}