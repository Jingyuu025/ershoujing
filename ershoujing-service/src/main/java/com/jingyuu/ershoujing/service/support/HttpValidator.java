package com.jingyuu.ershoujing.service.support;

import com.jingyuu.ershoujing.common.exception.JyuException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author owen
 * @date 2017-09-29
 */
public interface HttpValidator {

    /**
     * http请求验证器
     *
     * @param request
     * @throws JyuException
     */
    void validate(HttpServletRequest request) throws JyuException;
}
