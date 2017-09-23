package com.jingyuu.ershoujing.dao.mybatis.bo;

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
public class SmsCodeBo extends BaseBean {
    /**
     * 验证码类型
     */
    private Integer businessType;

    /**
     * 手机号
     */
    private String telephone;
}
