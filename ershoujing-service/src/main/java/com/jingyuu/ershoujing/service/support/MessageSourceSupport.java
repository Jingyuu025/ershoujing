package com.jingyuu.ershoujing.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceSupport {
    @Autowired
    private MessageSource messageSource;

    /**
     * @param code
     * @return
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.CHINA);
    }

    /**
     * @param code
     * @param args
     * @return
     */
    public String getMessage(String code, String... args) {
        return messageSource.getMessage(code, args, Locale.CHINA);
    }
}

