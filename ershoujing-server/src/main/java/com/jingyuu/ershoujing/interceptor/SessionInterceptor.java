package com.jingyuu.ershoujing.interceptor;

import com.google.gson.Gson;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.UserSessionEntity;
import com.jingyuu.ershoujing.service.system.UserSessionService;
import com.jingyuu.ershoujing.web.response.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 会话拦截器
 *
 * @author owen
 * @date 2017-09-23
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private Gson gson;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        String token = request.getHeader(JyuConstant.TOKEN_HEADER); // 获取访问令牌
        if (CommonUtil.isEmpty(token)) {
            return false;
        }

        // 查询用户会话信息
        UserSessionEntity userSessionEntity = userSessionService.getByAccessToken(token);
        if (CommonUtil.isEmpty(userSessionEntity)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(gson.toJson((BaseResp.fail("访问令牌无效或已过期"))));
            log.warn("访问令牌无效或已过期! 访问令牌:{}", token);
            return false;
        }

        return true;
    }
}
