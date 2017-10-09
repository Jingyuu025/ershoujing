package com.jingyuu.ershoujing.dao.jpa.repository.user;

import com.jingyuu.ershoujing.dao.jpa.entity.user.UserSessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface UserSessionRepository extends CrudRepository<UserSessionEntity, String> {
    /**
     * 查询用户会话列表
     *
     * @param accessToken 访问令牌
     */
    List<UserSessionEntity> findByAccessToken(String accessToken);

    /**
     * 查询用户会话列表
     *
     * @param refreshToken 刷新令牌
     */
    List<UserSessionEntity> findByRefreshToken(String refreshToken);

}
