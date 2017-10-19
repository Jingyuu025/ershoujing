package com.jingyuu.ershoujing.service.user.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.*;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.common.utils.DateUtil;
import com.jingyuu.ershoujing.common.utils.Md5Util;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.user.UserRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.*;
import com.jingyuu.ershoujing.dao.mybatis.vo.LoginVo;
import com.jingyuu.ershoujing.dao.mybatis.vo.UserSessionVo;
import com.jingyuu.ershoujing.service.support.event.ActionEvent;
import com.jingyuu.ershoujing.service.support.event.LoginSuccessEvent;
import com.jingyuu.ershoujing.service.system.SmsService;
import com.jingyuu.ershoujing.service.user.UserService;
import com.jingyuu.ershoujing.service.user.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jingyuu.ershoujing.service.support.UserSupport.generatorNickName;
import static com.jingyuu.ershoujing.service.support.UserSupport.generatorPassword;

/**
 * @author owen
 * @date 2017-09-07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private SmsService smsService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(readOnly = true)
    public UserEntity getByTelephone(String telephone) {
        List<UserEntity> userEntityList = userRepository.findByTelephone(telephone);
        if (CommonUtil.isEmpty(userEntityList) || userEntityList.size() > 1) {
            return null;
        }

        return userEntityList.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity loadByTelephone(String telephone) throws JyuException {
        UserEntity userEntity = this.getByTelephone(telephone);
        if (CommonUtil.isEmpty(userEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "用户不存在！手机号:" + telephone);
        }
        return userEntity;

    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity get(String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity load(String userId) throws JyuException {
        UserEntity userEntity = this.get(userId);
        if (CommonUtil.isEmpty(userEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "用户不存在! 用户编号:" + userId);
        }
        return userEntity;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public LoginVo login(LoginBo loginBo) throws JyuException {
        String telephone = loginBo.getTelephone();
        String password = loginBo.getPassword();
        String ip = loginBo.getIp();
        TerminalEnum terminal = loginBo.getTerminalEnum();

        // 查询用户信息
        UserEntity userEntity = userService.loadByTelephone(telephone);

        // 密码校验
        String salt = userEntity.getSalt(); // 盐值
        if (!userEntity.getPassword().equals(generatorPassword(password, salt))) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "手机号或密码错误");
        }

        // 验证用户状态
        int state = userEntity.getState();
        if (!UserStateEnum.OK.equals(UserStateEnum.fromValue(state))) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "用户状态异常");
        }

        // 更新最近登录时间，IP地址
        userEntity.setLastLoginIp(ip);
        userEntity.setLastLoginTime(DateUtil.currentDate());
        userRepository.save(userEntity);

        // 查询人员信息
        String userId = userEntity.getId();
        String nickName = userEntity.getNickName();

        // 发布登录成功事件(记录日志，增加积分)
        eventPublisher.publishEvent(LoginSuccessEvent.builder().userId(userId).ip(ip).build());

        // 发布登录行为事件
        eventPublisher.publishEvent(
                ActionEvent.builder()
                        .userId(userId)
                        .action(terminal.getName() + "登录")
                        .parameter(loginBo.toJsonString())
                        .ip(ip)
                        .build()
        );

        // 设置用户会话
        UserSessionVo userSessionVo = userSessionService.createToken(terminal, userId);
        String accessToken = userSessionVo.getAccessToken();

        return LoginVo.builder()
                .terminal(terminal)
                .userId(userId).nickName(nickName).telephone(telephone).role(RoleEnum.fromValue(userEntity.getRole()))
                .token(accessToken)
                .build();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public LoginVo smsLogin(LoginBo loginBo) throws JyuException {
        String telephone = loginBo.getTelephone();
        String code = loginBo.getCode();
        String ip = loginBo.getIp();
        TerminalEnum terminal = loginBo.getTerminalEnum();

        // 验证验证码
        SmsCodeBo smsCodeBo = SmsCodeBo.builder()
                .telephone(telephone)
                .businessType(SmsCodeBizEnum.LOGIN.getValue())
                .build();
        if (!smsService.checkSmsCode(smsCodeBo, code)) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "验证码错误");
        }

        // 查询用户信息
        UserEntity userEntity = userService.loadByTelephone(telephone);

        // 验证用户状态
        int state = userEntity.getState();
        if (!UserStateEnum.OK.equals(UserStateEnum.fromValue(state))) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "用户状态异常");
        }

        // 更新最近登录时间，IP地址
        userEntity.setLastLoginIp(ip);
        userEntity.setLastLoginTime(DateUtil.currentDate());
        userRepository.save(userEntity);

        // 查询人员信息
        String userId = userEntity.getId();
        String nickName = userEntity.getNickName();

        // 发布登录成功事件(记录日志，增加积分)
        eventPublisher.publishEvent(LoginSuccessEvent.builder().userId(userId).ip(ip).build());

        // 发布登录行为事件
        eventPublisher.publishEvent(
                ActionEvent.builder()
                        .userId(userId).ip(ip)
                        .action(terminal.getName() + "短信登录")
                        .parameter(loginBo.toJsonString())
                        .build()
        );

        // 设置用户会话
        UserSessionVo userSessionVo = userSessionService.createToken(terminal, userId);
        String accessToken = userSessionVo.getAccessToken();

        return LoginVo.builder()
                .terminal(terminal)
                .userId(userId).nickName(nickName).telephone(telephone).role(RoleEnum.fromValue(userEntity.getRole()))
                .token(accessToken)
                .build();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void register(RegisterBo registerBo) throws JyuException {
        // 验证验证码
        String telephone = registerBo.getTelephone();
        String code = registerBo.getCode();
        String ip = registerBo.getIp();
        SmsCodeBo smsCodeBo = SmsCodeBo.builder()
                .telephone(telephone)
                .businessType(SmsCodeBizEnum.REGISTER.getValue())
                .build();
        if (!smsService.checkSmsCode(smsCodeBo, code)) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "验证码错误");
        }

        // 去重
        UserEntity userEntity = userService.getByTelephone(telephone);
        if (CommonUtil.isNotEmpty(userEntity)) {
            throw new JyuException(ErrorEnum.DATA_REPEAT_ERROR, "用户已经注册! 手机号:" + telephone);
        }

        // 注册
        String salt = Md5Util.md5(String.valueOf(System.currentTimeMillis())); // 盐值
        String password = registerBo.getPassword(); // 密码
        String nickName = generatorNickName(telephone); // 昵称
        if (CommonUtil.isEmpty(password)) {
            password = generatorPassword(telephone);
        }

        userEntity = UserEntity.builder()
                .telephone(telephone)
                .password(generatorPassword(password, salt))
                .nickName(nickName)
                .salt(salt)
                .state(UserStateEnum.OK.getValue())
                .lastLoginIp(registerBo.getIp())
                .lastLoginTime(DateUtil.currentDate())
                .build();
        userEntity = userRepository.save(userEntity);

        String userId = userEntity.getId();

        // 发布注册行为事件
        eventPublisher.publishEvent(
                ActionEvent.builder()
                        .userId(userId)
                        .action("注册")
                        .parameter(registerBo.toJsonString())
                        .ip(ip)
                        .build()
        );
    }

    /**
     * 更新密码
     *
     * @param modifyPasswordBo 更新密码表单
     * @throws JyuException
     */
    public void modifyPassword(ModifyPasswordBo modifyPasswordBo) throws JyuException {
        String telephone = modifyPasswordBo.getTelephone();
        String oldPassword = modifyPasswordBo.getOldPassword(); // 原密码
        String newPassword = modifyPasswordBo.getNewPassword(); // 新密码

        // 查询用户详情
        UserEntity userEntity = this.loadByTelephone(telephone);
        String salt = userEntity.getSalt();
        String password = userEntity.getPassword();

        // 校验原密码
        if (!generatorPassword(oldPassword, salt).equals(password)) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "原密码不正确");
        }

        // 更新盐值、密码
        salt = Md5Util.md5(String.valueOf(System.currentTimeMillis())); // 盐值
        userEntity.setSalt(salt);
        userEntity.setPassword(generatorPassword(newPassword, salt));
        userRepository.save(userEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void retrievalPassword(RetrievalPasswordBo retrievalPasswordBo) throws JyuException {
        String telephone = retrievalPasswordBo.getTelephone();
        String code = retrievalPasswordBo.getCode();
        String password = retrievalPasswordBo.getPassword();

        // 验证验证码
        SmsCodeBo smsCodeBo = SmsCodeBo.builder()
                .telephone(telephone)
                .businessType(SmsCodeBizEnum.RETRIEVAL_PASSWORD.getValue())
                .build();
        if (!smsService.checkSmsCode(smsCodeBo, code)) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "验证码错误");
        }

        // 查询用户信息
        UserEntity userEntity = userService.loadByTelephone(telephone);

        // 验证用户状态
        int state = userEntity.getState();
        if (!UserStateEnum.OK.equals(UserStateEnum.fromValue(state))) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "用户状态异常");
        }

        // 更新盐值、密码
        String salt = Md5Util.md5(String.valueOf(System.currentTimeMillis())); // 盐值
        userEntity.setSalt(salt);
        userEntity.setPassword(generatorPassword(password, salt));
        userRepository.save(userEntity);
    }
}
