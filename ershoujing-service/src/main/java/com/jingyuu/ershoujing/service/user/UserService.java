package com.jingyuu.ershoujing.service.user;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.UserEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.LoginBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.RegisterBo;

/**
 * @author owen
 * @date 2017-09-07
 */
public interface UserService {
    /**
     * 查询用户详情
     *
     * @param telephone 手机号
     * @return
     * @throws JyuException
     */
    UserEntity getByTelephone(String telephone);

    /**
     * 查询用户详情
     *
     * @param telephone 手机号
     * @return
     * @throws JyuException
     */
    UserEntity loadByTelephone(String telephone) throws JyuException;

    /**
     * 查询用户详情
     *
     * @param userId 用户编号
     * @return
     */
    UserEntity get(String userId);

    /**
     * 查询用户详情
     *
     * @param userId 用户编号
     * @return
     * @throws JyuException
     */
    UserEntity load(String userId) throws JyuException;



}
