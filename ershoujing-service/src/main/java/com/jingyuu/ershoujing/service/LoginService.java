package com.jingyuu.ershoujing.service;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.UserEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.LoginBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.RegisterBo;

/**
 * @author owen
 * @date 2017-09-07
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param loginBo 登录表单
     * @return
     */
    UserEntity login(LoginBo loginBo) throws JyuException;

    /**
     * 快速登录
     * @param loginBo 登录表单
     * @return
     * @throws JyuException
     */
    UserEntity smsLogin(LoginBo loginBo) throws JyuException;


    /**
     * 注册
     *
     * @param registerBo 注册表单
     * @throws JyuException
     */
    void register(RegisterBo registerBo) throws JyuException;
}
