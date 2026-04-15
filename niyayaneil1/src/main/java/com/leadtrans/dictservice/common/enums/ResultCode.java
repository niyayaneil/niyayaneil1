package com.leadtrans.dictservice.common.enums;
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "操作成功"),

    /* 客户端错误 4xx */
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    CONFLICT(409, "资源已存在"),

    /* 服务端错误 5xx */
    INTERNAL_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    /* 业务错误码（6xxxx）*/
    USER_NOT_EXIST(60001, "用户不存在"),
    INVALID_TOKEN(60002, "无效的Token");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // Getters
    public int code() { return code; }
    public String msg() { return msg; }
}