package com.jingyuu.ershoujing.service.system;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.SmsEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.SmsCodeBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.SmsCodeVo;

/**
 * @author owen
 * @date 2017-09-15
 */
public interface SmsService {

    /**
     * 获取验证码
     *
     * @param smsCodeBo
     * @return
     * @throws JyuException
     */
    SmsCodeVo getSmsCode(SmsCodeBo smsCodeBo);

    /**
     * @param smsCodeBo
     * @return
     * @throws JyuException
     */
    SmsCodeVo loadSmsCode(SmsCodeBo smsCodeBo) throws JyuException;


    /**
     * 创建短信验证码
     *
     * @param smsCodeBo
     * @return
     */
    SmsCodeVo createSmsCode(SmsCodeBo smsCodeBo);

    /**
     * 验证短信验证码
     *
     * @param smsCodeBo
     * @param code
     * @return
     */
    boolean checkSmsCode(SmsCodeBo smsCodeBo, String code);

    /**
     * 发送短信
     *
     * @param sms
     */
    void sendSms(SmsEntity sms);
}
