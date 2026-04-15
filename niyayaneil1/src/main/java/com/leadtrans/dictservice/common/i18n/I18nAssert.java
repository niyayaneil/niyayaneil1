package com.leadtrans.dictservice.common.i18n;

import com.leadtrans.dictservice.common.enums.ResultCode;
import com.leadtrans.dictservice.common.exception.BizException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

import java.util.Collection;

public final class I18nAssert {

    private static MessageSource messageSource;

    // 通过依赖注入设置 MessageSource（需在 Spring 容器中初始化）
    public static void setMessageSource(MessageSource messageSource) {
        I18nAssert.messageSource = messageSource;
    }
    /**
     * 断言对象已存在（国际化消息）
     */
    public static void exists(Integer count, String messageKey, Object... args) {
        if(count!=null && count>0){
            String message = getMessage(messageKey, args);
            throw new BizException(ResultCode.CONFLICT,message);
        }
    }
    /**
     * 断言对象不存在（国际化消息）
     */
    public static void notFound(Object object, String messageKey, Object... args) {
        if(object==null || (object instanceof Collection && ((Collection)object).isEmpty())){
            String message = getMessage(messageKey, args);
            throw new BizException(ResultCode.NOT_FOUND,message);
        }
    }

    /**
     * 断言参数错误（国际化消息）
     */
    public static void badRequest(Object object, String messageKey, Object... args) {
        if(object==null){
            String message = getMessage(messageKey, args);
            throw new BizException(ResultCode.BAD_REQUEST,message);
        }
    }

    /**
     * 断言表达式为 true（国际化消息）
     */
    public static void isTrue(boolean expression, String messageKey, Object... args) {
        String message = getMessage(messageKey, args);
        Assert.isTrue(expression, message);
    }

    /**
     * 获取国际化消息
     */
    private static String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }
}
