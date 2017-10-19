package com.jingyuu.ershoujing.web.controller;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserSessionEntity;
import com.jingyuu.ershoujing.service.user.UserService;
import com.jingyuu.ershoujing.service.user.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author owen
 * @date 2017-09-22
 */
@Controller
public class BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserSessionService userSessionService;

    /**
     * 获取当前登录用户
     *
     * @param token 访问令牌
     * @return
     * @throws JyuException
     */
    public UserEntity getUser(String token) throws JyuException {
        // 查询用户会话
        String userId = this.getUserId(token);
        return userService.load(userId);
    }

    /**
     * 获取当前登录用户编号
     *
     * @param token 访问令牌
     * @return
     * @throws JyuException
     */
    public String getUserId(String token) throws JyuException {
        // 查询用户会话
        UserSessionEntity userSessionEntity = userSessionService.loadByAccessToken(token);
        return userSessionEntity.getUserId();
    }

    /**
     * 获取当前登录用户手机号
     *
     * @param token 访问令牌
     * @return
     * @throws JyuException
     */
    public String getUserTelephone(String token) throws JyuException {
        UserEntity userEntity = this.getUser(token);
        return userEntity.getTelephone();
    }

}
