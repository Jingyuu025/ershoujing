package com.jingyuu.ershoujing.dao.mybatis.vo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsCodeVo extends BaseBean {
    /**
     * 编号
     */
    private Long id;

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
     * 验证码失效时长
     */
    private Integer expireIn;
}
