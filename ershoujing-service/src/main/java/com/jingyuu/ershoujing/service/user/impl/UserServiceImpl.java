package com.jingyuu.ershoujing.service.user.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.UserRepository;
import com.jingyuu.ershoujing.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity getByTelephone(String telephone) {
        List<UserEntity> userEntityList = userRepository.findByTelephone(telephone);
        if (CommonUtil.isEmpty(userEntityList) || userEntityList.size() > 1) {
            return null;
        }

        return userEntityList.get(0);
    }

    @Override
    public UserEntity loadByTelephone(String telephone) throws JyuException {
        UserEntity userEntity = this.getByTelephone(telephone);
        if (CommonUtil.isEmpty(userEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "用户不存在！手机号:" + telephone);
        }
        return userEntity;

    }

    @Override
    public UserEntity get(String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public UserEntity load(String userId) throws JyuException {
        UserEntity userEntity = this.get(userId);
        if (CommonUtil.isEmpty(userEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "用户不存在! 用户编号:" + userId);
        }
        return userEntity;
    }


}
