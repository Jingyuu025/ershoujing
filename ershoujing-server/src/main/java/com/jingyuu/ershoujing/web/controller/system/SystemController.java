package com.jingyuu.ershoujing.web.controller.system;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.mybatis.bo.SmsCodeBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.SmsCodeVo;
import com.jingyuu.ershoujing.service.system.SmsService;
import com.jingyuu.ershoujing.web.controller.BaseController;
import com.jingyuu.ershoujing.web.request.SmsCodeRequest;
import com.jingyuu.ershoujing.web.response.BaseResp;
import com.jingyuu.ershoujing.web.response.SmsCodeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author owen
 * @date 2017-09-07
 */
@Api(tags = "短信服务")
@RequestMapping(value = "/system")
@RestController
public class SystemController extends BaseController {
    @Autowired
    private SmsService smsService;

    /**
     * 获取短信验证码
     */
    @ApiOperation(value = "获取短信验证码")
    @RequestMapping(value = "/sms-code", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<SmsCodeResult>> getSmsCode(@RequestBody @Valid SmsCodeRequest smsCodeRequest)
            throws JyuException {
        String telephone = smsCodeRequest.getTelephone();
        Integer businessType = smsCodeRequest.getBusinessType();
        // 构建SmsCodeBo
        SmsCodeBo smsCodeBo = SmsCodeBo.builder()
                .telephone(telephone)
                .businessType(businessType)
                .build();

        // 获取短信验证码
        SmsCodeVo smsCodeVo = smsService.createSmsCode(smsCodeBo);
        return ResponseEntity.ok(BaseResp.ok(
                SmsCodeResult.builder()
                        .telephone(telephone)
                        .serialNumber(smsCodeVo.getSerialNumber())
                        .code(smsCodeVo.getCode())
                        .expireIn(smsCodeVo.getExpireIn())
                        .build()
        ));
    }

}
