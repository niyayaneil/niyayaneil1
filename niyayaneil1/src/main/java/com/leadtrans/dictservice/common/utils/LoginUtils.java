package com.leadtrans.dictservice.common.utils;

import com.leadtrans.dictservice.common.enums.HeaderNames;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class LoginUtils {

    public static String getUsername() {
        String username = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(Objects.nonNull(attributes)){
            HttpServletRequest request = attributes.getRequest();
            if (request != null) {
                username = request.getHeader(HeaderNames.username);
            }
        }
        return username==null?"":username;
    }
}
