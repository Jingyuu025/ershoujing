package com.jingyuu.ershoujing.dao.mybatis.mapper;

import com.jingyuu.ershoujing.dao.jpa.entity.sytem.SmsCodeEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.SmsCodeBo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-08
 */
@Repository
public interface SmsCodeMapper {

    /**
     * 查询有效的短信验证码
     *
     * @param smsCodeBo
     * @return
     */
    List<SmsCodeEntity> listValidSmsCode(SmsCodeBo smsCodeBo);
}
