package com.leadtrans.dictservice.common.config;

import com.leadtrans.dictservice.common.i18n.I18nAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AssertInitializer {

    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    public void init() {
        I18nAssert.setMessageSource(messageSource);
    }
}