package com.leadtrans.dictservice.common.config;

import com.leadtrans.dictservice.common.enums.ResultCode;
import com.leadtrans.dictservice.common.exception.BizException;
import com.leadtrans.dictservice.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BizException.class)
    public ResultVO<?> handleBusinessEx(BizException e) {
        return ResultVO.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ResultVO.fail(ResultCode.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<?> handleValidationException(MethodArgumentNotValidException ex, Locale locale ) {
            Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(error -> error.getField(), error -> {
            try {
                String messageKey = error.getObjectName() + "." + error.getField() + "." + error.getCode();
                String message = messageSource.getMessage(messageKey, error.getArguments(), locale);
                if (StringUtils.hasText(message)) {
                    return message;
                }
            }catch (NoSuchMessageException e){
            }
            return error.getDefaultMessage();
        }, (e1, e2) -> e1));

        String errorMessage = errors.values().stream().findFirst().get();
        ResultVO<Object> resultVO = ResultVO.fail(ResultCode.BAD_REQUEST, errorMessage);
        resultVO.setData(Map.of("errors",errors));
        return resultVO;
    }
}