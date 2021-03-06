package com.jingyuu.ershoujing.service.system.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.*;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.common.utils.DateUtil;
import com.jingyuu.ershoujing.common.utils.RandomUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.SmsCodeEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.SmsEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.system.SmsCodeRepository;
import com.jingyuu.ershoujing.dao.jpa.repository.system.SmsRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.SmsCodeBo;
import com.jingyuu.ershoujing.dao.mybatis.mapper.SmsCodeMapper;
import com.jingyuu.ershoujing.dao.mybatis.vo.SmsCodeVo;
import com.jingyuu.ershoujing.service.support.MessageSourceSupport;
import com.jingyuu.ershoujing.service.system.SmsService;
import com.jingyuu.ershoujing.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jingyuu.ershoujing.common.statics.enums.SmsCodeBizEnum.REGISTER;

/**
 * @author owen
 * @date 2017-09-15
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    // 短信验证码前缀
    public static final String SMS_CODE_PREFIX = "sms.code.";
    // 获取短信验证码不需要校验用户（状态）的业务
    private static final SmsCodeBizEnum[] VALIDATE_USER_EXCLUDES_BIZ = {
            REGISTER                              // 注册
    };
    // 短信验证码失效时长（单位：秒）
    @Value("${sms.code.expire-in}")
    private String smsCodeExpireIn;

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSourceSupport messageSupport;
    @Autowired
    private SmsRepository smsRepository;
    @Autowired
    private SmsCodeRepository smsCodeRepository;
    @Autowired
    private SmsCodeMapper smsCodeMapper;

    @Override
    @Transactional(readOnly = true)
    public SmsCodeVo getSmsCode(SmsCodeBo smsCodeBo) {
        // 查询有效的验证码
        List<SmsCodeEntity> smsCodeList = smsCodeMapper.listValidSmsCode(smsCodeBo);
        if (CommonUtil.isEmpty(smsCodeList)) {
            return null;
        }

        SmsCodeEntity smsCodeEntity = smsCodeList.get(0);
        // 构建SmsCodeVo
        SmsCodeVo smsCodeVo = SmsCodeVo.builder()
                .id(smsCodeEntity.getId())
                .telephone(smsCodeEntity.getTelephone())
                .code(smsCodeEntity.getCode())
                .serialNumber(smsCodeEntity.getSerialNumber())
                .expireIn(smsCodeEntity.getExpireIn())
                .build();
        return smsCodeVo;
    }

    @Override
    @Transactional(readOnly = true)
    public SmsCodeVo loadSmsCode(SmsCodeBo smsCodeBo) throws JyuException {
        SmsCodeVo smsCodeVo = this.getSmsCode(smsCodeBo);
        if (CommonUtil.isEmpty(smsCodeVo)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "短信验证码不存在");
        }

        return smsCodeVo;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public SmsCodeVo createSmsCode(SmsCodeBo smsCodeBo) throws JyuException {
        String telephone = smsCodeBo.getTelephone(); // 手机号
        Integer businessType = smsCodeBo.getBusinessType(); // 业务类型
        SmsCodeBizEnum smsCodeBizEnum = SmsCodeBizEnum.fromValue(businessType);

        // 非排除之外的业务获取短信验证码时需要校验用户信息
        if (!ArrayUtils.contains(VALIDATE_USER_EXCLUDES_BIZ, smsCodeBizEnum)) {
            // 查询用户信息
            UserEntity userEntity = userService.loadByTelephone(telephone);
            int state = userEntity.getState();
            if (!UserStateEnum.OK.equals(UserStateEnum.fromValue(state))) {
                throw new JyuException(ErrorEnum.DATA_IS_ERROR, "用户状态异常,手机号:" + telephone);
            }
        }

        SmsCodeVo smsCodeVo = this.getSmsCode(smsCodeBo);
        if (CommonUtil.isEmpty(smsCodeVo)) {
            // 创建短信验证码
            int expireInSeconds = Integer.parseInt(smsCodeExpireIn);
            int expireInMinutes = expireInSeconds / 60;
            String code = RandomUtil.createRandomNumber(4); // 验证码
            String serialNumber = RandomUtil.createRandomNumberAndChar(6); // 序列号

            SmsCodeEntity smsCodeEntity = SmsCodeEntity.builder()
                    .telephone(telephone)
                    .businessType(businessType)
                    .code(code)
                    .serialNumber(serialNumber)
                    .expireIn(expireInSeconds)
                    .expireTime(DateUtils.addMinutes(DateUtil.currentDate(), expireInSeconds))
                    .state(SmsCodeStateEnum.WAITING_VERIFY.getValue())
                    .build();
            smsCodeRepository.save(smsCodeEntity);

            // 发送短信
            String content = messageSupport.getMessage(SMS_CODE_PREFIX + smsCodeBizEnum.getAlias(),
                    code,
                    String.valueOf(expireInMinutes));
            SmsEntity smsEntity = SmsEntity.builder()
                    .telephone(telephone)
                    .content(content)
                    .state(SmsStateEnum.DEALING.getValue())
                    .build();
            this.sendSms(smsEntity);

            // 构建SmsCodeVo
            smsCodeVo = SmsCodeVo.builder()
                    .telephone(smsCodeEntity.getTelephone())
                    .code(smsCodeEntity.getCode())
                    .serialNumber(smsCodeEntity.getSerialNumber())
                    .expireIn(expireInMinutes)
                    .build();
        }

        return smsCodeVo;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkSmsCode(SmsCodeBo smsCodeBo, String code) {
        SmsCodeVo smsCodeVo = this.getSmsCode(smsCodeBo);
        if (CommonUtil.isEmpty(smsCodeVo)) {
            return false;
        }

        // 验证码匹配
        if (smsCodeVo.getCode().equals(code)) {
            long id = smsCodeVo.getId(); // 短信验证码编号

            // 查询短信验证码详情
            SmsCodeEntity smsCodeEntity = smsCodeRepository.findOne(id);
            smsCodeEntity.setState(SmsCodeStateEnum.VERIFIED.getValue());
            smsCodeRepository.save(smsCodeEntity);

            return true;
        }

        return false;
    }

    /**
     * 异步发送短信
     *
     * @param sms
     */
    // FIXME: 20/09/2017 对接短信通道
    @Override
    @Async
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void sendSms(SmsEntity sms) {
        String telephone = sms.getTelephone();
        String content = sms.getContent();

        smsRepository.save(sms);
        log.info("发送短信... 接受者:{},内容:{} ", telephone, content);
    }
}
