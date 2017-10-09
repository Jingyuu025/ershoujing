package com.jingyuu.ershoujing.service.user;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.TerminalEnum;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserSessionEntity;
import com.jingyuu.ershoujing.dao.mybatis.vo.UserSessionVo;

/**
 * @author owen
 * @date 2017-09-07
 */
public interface UserSessionService {
    /**
     * 创建令牌
     *
     * @param terminalEnum 终端
     * @param userId       用户编号
     * @return
     */
    UserSessionVo createToken(TerminalEnum terminalEnum, String userId) throws JyuException;

    /**
     * 刷新令牌
     *
     * @param refreshToken 刷新令牌
     */
    UserSessionVo refreshToken(String refreshToken) throws JyuException;

    /**
     * 查询用户会话详情
     *
     * @param userId 用户编号
     */
    UserSessionEntity get(String userId);

    /**
     * 查询用户会话详情
     *
     * @param userId 用户编号
     * @throws JyuException
     */
    UserSessionEntity load(String userId) throws JyuException;

    /**
     * 查询用户会话详情
     *
     * @param accessToken 访问令牌
     */
    UserSessionEntity getByAccessToken(String accessToken);

    /**
     * 查询用户会话详情
     *
     * @param accessToken 访问令牌
     * @throws JyuException
     */
    UserSessionEntity loadByAccessToken(String accessToken) throws JyuException;

    /**
     * 查询用户会话详情
     *
     * @param refreshToken 刷新令牌
     */
    UserSessionEntity getByRefreshToken(String refreshToken);

    /**
     * 查询用户会话详情
     *
     * @param refreshToken 刷新令牌
     * @throws JyuException
     */
    UserSessionEntity loadByRefreshToken(String refreshToken) throws JyuException;

}
