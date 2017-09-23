package com.jingyuu.ershoujing.web.response;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsCodeResult extends BaseBean {
    /**
     * 手机号
     */
    private String telephone;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码失效期
     */
    private Integer expireIn;
}
