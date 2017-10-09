package com.jingyuu.ershoujing.service.user.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.statics.enums.TerminalEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.common.utils.DateUtil;
import com.jingyuu.ershoujing.common.utils.Md5Util;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserSessionEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.user.UserSessionRepository;
import com.jingyuu.ershoujing.dao.mybatis.vo.UserSessionVo;
import com.jingyuu.ershoujing.service.user.UserService;
import com.jingyuu.ershoujing.service.user.UserSessionService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author owen
 * @date 2017-09-13
 */
@Service
public class UserSessionServiceImpl implements UserSessionService {
    // 移动端访问Token过期时间：2天,刷新Token过期时间：7天
    private final int ACCESS_TOKEN_EXPIRE_SECONDS_MOBILE = 60 * 60 * 24 * 2;
    private final int REFRESH_TOKEN_EXPIRE_SECONDS_MOBILE = 60 * 60 * 24 * 7;

    // PC端 访问Token过期时间：45分钟,刷新Token过期时间: 1天
    private final int ACCESS_TOKEN_EXPIRE_SECONDS_PC = 60 * 45;
    private final int REFRESH_TOKEN_EXPIRE_SECONDS_PC = 60 * 60 * 24;

    @Autowired
    private UserService userService;
    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    @Transactional(readOnly = true)
    public UserSessionEntity get(String userId) {
        return userSessionRepository.findOne(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserSessionEntity load(String userId) throws JyuException {
        UserSessionEntity userSessionEntity = this.get(userId);
        if (CommonUtil.isEmpty(userSessionEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "用户会话不存在,用户编号:" + userId);
        }
        return userSessionEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public UserSessionEntity getByAccessToken(String accessToken) {
        List<UserSessionEntity> userSessionList = userSessionRepository.findByAccessToken(accessToken);
        if (CommonUtil.isEmpty(userSessionList)) {
            return null;
        }

        UserSessionEntity userSessionEntity = userSessionList.get(0);
        // 校验访问令牌
        Date expireTime = userSessionEntity.getExpireIn(); // 访问令牌过期时间
        if (this.isExpire(expireTime)) {
            return null;
        }

        return userSessionEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public UserSessionEntity loadByAccessToken(String accessToken) throws JyuException {
        UserSessionEntity userSessionEntity = this.getByAccessToken(accessToken);
        if (CommonUtil.isEmpty(userSessionEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "用户会话不存在,访问令牌:" + accessToken);
        }

        Date expireTime = userSessionEntity.getExpireIn(); // 访问令牌过期时间
        String userId = userSessionEntity.getUserId(); // 用户编号
        if (this.isExpire(expireTime)) {
            throw new JyuException(ErrorEnum.USER_SESSION_TOKEN_EXPIRED, "用户访问令牌过期,用户编号：" + userId);
        }

        return userSessionEntity;
    }


    @Override
    @Transactional(readOnly = true)
    public UserSessionEntity getByRefreshToken(String refreshToken) {
        List<UserSessionEntity> userSessionList = userSessionRepository.findByRefreshToken(refreshToken);
        if (CommonUtil.isEmpty(userSessionList)) {
            return null;
        }

        UserSessionEntity userSessionEntity = userSessionList.get(0);
        // 校验访问令牌
        Date refreshExpireTime = userSessionEntity.getRefreshExpireIn(); // 刷新令牌过期时间
        if (this.isExpire(refreshExpireTime)) {
            return null;
        }

        return userSessionEntity;
    }


    @Override
    @Transactional(readOnly = true)
    public UserSessionEntity loadByRefreshToken(String refreshToken) throws JyuException {
        UserSessionEntity userSessionEntity = this.getByRefreshToken(refreshToken);
        if (CommonUtil.isEmpty(userSessionEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "用户会话不存在,刷新令牌:" + refreshToken);
        }

        Date refreshExpireTime = userSessionEntity.getRefreshExpireIn(); //  刷新令牌过期时间
        String userId = userSessionEntity.getUserId(); // 用户编号
        if (this.isExpire(refreshExpireTime)) {
            throw new JyuException(ErrorEnum.USER_SESSION_TOKEN_EXPIRED, "用户刷新令牌过期,用户编号：" + userId);
        }

        return userSessionEntity;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public UserSessionVo createToken(TerminalEnum terminal, String userId) throws JyuException {
        // 查询用户信息
        UserEntity userEntity = userService.load(userId);
        String telephone = userEntity.getTelephone();
        String nickName = userEntity.getNickName();

        // 创建令牌相关信息
        String accessToken = this.createAccessToken();
        Date expireIn = this.createExpireIn(terminal);
        Date grantTime = DateUtil.currentDate();

        String refreshToken = this.createRefreshToken();
        Date refreshExpireIn = this.createRefreshExpireIn(terminal);

        // 保存令牌信息
        UserSessionEntity userSessionEntity = this.get(userId);
        if (CommonUtil.isEmpty(userSessionEntity)) {
            userSessionEntity = UserSessionEntity.builder()
                    .userId(userId)
                    .nickName(nickName)
                    .telephone(telephone)
                    .build();
        }
        userSessionEntity.setAccessToken(accessToken);
        userSessionEntity.setGrantTime(grantTime);
        userSessionEntity.setExpireIn(expireIn);
        userSessionEntity.setRefreshToken(refreshToken);
        userSessionEntity.setRefreshExpireIn(refreshExpireIn);
        userSessionRepository.save(userSessionEntity);

        return UserSessionVo.builder()
                .accessToken(accessToken)
                .grantTime(grantTime)
                .expireIn(expireIn)
                .refreshToken(refreshToken)
                .refreshExpireIn(refreshExpireIn)
                .build();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public UserSessionVo refreshToken(String refreshToken) throws JyuException {

        return null;
    }

    /**
     * 判断时间是否过期
     *
     * @param expireTime
     * @return
     */
    public boolean isExpire(Date expireTime) {
        Date currentDate = DateUtil.currentDate(); // 系统当前时间
        return expireTime.before(currentDate) || expireTime.equals(currentDate);
    }

    /**
     * 生成访问令牌
     */
    private final String createAccessToken() {
        return getRandomTokenString();
    }

    /**
     * 生成访问令牌过期时间
     */
    private final Date createExpireIn(TerminalEnum terminal) {
        return getExpireDate(getAccessTokenExpireSeconds(terminal));
    }

    /**
     * 生成刷新令牌
     */
    private final String createRefreshToken() {
        return getRandomTokenString();
    }


    /**
     * 生成刷新令牌过期时间
     */
    private final Date createRefreshExpireIn(TerminalEnum terminal) {
        return getExpireDate(getRefreshTokenExpireSeconds(terminal));
    }

    /**
     * 生成过期时间
     *
     * @param seconds
     */
    private final Date getExpireDate(Integer seconds) {
        return DateUtils.addSeconds(DateUtil.currentDate(), seconds);
    }

    /**
     * 生成随机字符串
     */
    private final String getRandomTokenString() {
        return Md5Util.md5(UUID.randomUUID().toString() + String.valueOf(System.currentTimeMillis()));
    }

    /**
     * 获取访问令牌过期时间（单位：秒）
     *
     * @param terminal 终端
     * @return
     */
    private int getAccessTokenExpireSeconds(TerminalEnum terminal) {
        if (terminal.equals(TerminalEnum.PC)) {
            return ACCESS_TOKEN_EXPIRE_SECONDS_PC;
        }

        return ACCESS_TOKEN_EXPIRE_SECONDS_MOBILE;
    }

    /**
     * 获取刷新令牌过期时间（单位：秒）
     *
     * @param terminal 终端
     * @return
     */
    private int getRefreshTokenExpireSeconds(TerminalEnum terminal) {
        if (terminal.equals(TerminalEnum.PC)) {
            return REFRESH_TOKEN_EXPIRE_SECONDS_PC;
        }

        return REFRESH_TOKEN_EXPIRE_SECONDS_MOBILE;
    }
}
