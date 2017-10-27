package com.jingyuu.ershoujing.web.controller;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserSessionEntity;
import com.jingyuu.ershoujing.service.support.RedisService;
import com.jingyuu.ershoujing.service.user.UserService;
import com.jingyuu.ershoujing.service.user.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.jingyuu.ershoujing.common.statics.constants.RedisKeyConstant.USER_INFO;

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
    @Autowired
    private RedisService redisService;

    /**
     * 获取当前登录用户
     *
     * @param token 访问令牌
     * @return
     * @throws JyuException
     */
    public UserEntity getUser(String token) throws JyuException {
        // 查询用户会话
        UserEntity userEntity = (UserEntity) redisService.get(USER_INFO + token);
        if (CommonUtil.isEmpty(userEntity)) {
            UserSessionEntity userSessionEntity = userSessionService.loadByAccessToken(token);
            String userId = userSessionEntity.getUserId();
            userEntity = userService.load(userId);
        }
        return userEntity;
    }

    /**
     * 获取当前登录用户编号
     *
     * @param token 访问令牌
     * @return
     * @throws JyuException
     */
    public String getUserId(String token) throws JyuException {
        UserEntity userEntity = this.getUser(token);
        return userEntity.getId();
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

    /**
     * 获取当前登录用户昵称
     *
     * @param token 访问令牌
     * @return
     * @throws JyuException
     */
    public String getUserNickname(String token) throws JyuException {
        UserEntity userEntity = this.getUser(token);
        return userEntity.getNickName();
    }
}
