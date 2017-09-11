package com.jingyuu.ershoujing.service.user.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.statics.enums.UserStateEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.common.utils.DateUtil;
import com.jingyuu.ershoujing.common.utils.Md5Util;
import com.jingyuu.ershoujing.dao.jpa.entity.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.UserRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.LoginBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.RegisterBo;
import com.jingyuu.ershoujing.service.LoginService;
import com.jingyuu.ershoujing.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author owen
 * @date 2017-09-08
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity login(LoginBo loginBo) throws JyuException {
        String telephone = loginBo.getTelephone();
        String password = loginBo.getPassword();

        UserEntity userEntity = userService.loadByTelephone(telephone);

        // 密码校验
        String salt = userEntity.getSalt();
        String password_ = userEntity.getPassword();
        if (!password_.equals(this.passwordGenerator(password, salt))) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "手机号或密码错误");
        }
        return userEntity;
    }

    @Override
    public UserEntity smsLogin(LoginBo loginBo) throws JyuException{

        // TODO: 08/09/2017
        return null;
    }

    @Override
    public void register(RegisterBo registerBo) throws JyuException {
        // 参数校验
        registerBo.checkParam();

        // 去重
        String telephone = registerBo.getTelephone();
        UserEntity userEntity = userService.getByTelephone(telephone);
        if (CommonUtil.isNotEmpty(userEntity)) {
            throw new JyuException(ErrorEnum.DATA_REPEAT_ERROR, "用户已经注册! 手机号:" + telephone);
        }

        // 注册
        String salt = Md5Util.md5(String.valueOf(System.currentTimeMillis())); // 盐值
        userEntity = UserEntity.builder()
                .telephone(telephone)
                .password(this.passwordGenerator(registerBo.getPassword(), salt))
                .salt(salt)
                .state(UserStateEnum.OK.getValue())
                .lastLoginIp(registerBo.getIp())
                .lastLoginTime(DateUtil.currentDate())
                .build();
        userRepository.save(userEntity);
    }


    /**
     * 生成密码
     *
     * @param password 密码明文
     * @param salt     盐值
     * @return 密码MD5
     */
    private String passwordGenerator(String password, String salt) {
        return Md5Util.md5(salt + password + salt);
    }
}
