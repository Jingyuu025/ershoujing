package com.jingyuu.ershoujing.service.user;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.LoginBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.ModifyPasswordBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.RegisterBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.RetrievalPasswordBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.LoginVo;

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


    /**
     * 登录
     *
     * @param loginBo 登录表单
     * @return
     */
    LoginVo login(LoginBo loginBo) throws JyuException;

    /**
     * 短信登录
     *
     * @param loginBo 登录表单
     * @return
     * @throws JyuException
     */
    LoginVo smsLogin(LoginBo loginBo) throws JyuException;

    /***
     * 注册
     *
     * 用户未设置手机号码，系统自动为其分配密码
     *
     * @param registerBo 注册表单
     * @throws JyuException
     */
    void register(RegisterBo registerBo) throws JyuException;

    /**
     * 更新密码
     *
     * @param modifyPasswordBo 更新密码表单
     * @throws JyuException
     */
    void modifyPassword(ModifyPasswordBo modifyPasswordBo) throws JyuException;


    /**
     * 找回密码
     *
     * @param retrievalPasswordBo 找回密码表单
     * @throws JyuException
     */
    void retrievalPassword(RetrievalPasswordBo retrievalPasswordBo) throws JyuException;
}
