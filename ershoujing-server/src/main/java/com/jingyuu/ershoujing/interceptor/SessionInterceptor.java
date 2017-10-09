package com.jingyuu.ershoujing.interceptor;

import com.jingyuu.ershoujing.service.support.HttpValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 会话拦截器
 *
 * @author owen
 * @date 2017-09-23
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private List<HttpValidator> httpValidatorList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        for (HttpValidator validator : httpValidatorList) {
            validator.validate(request);
        }

        return true;
    }
}
