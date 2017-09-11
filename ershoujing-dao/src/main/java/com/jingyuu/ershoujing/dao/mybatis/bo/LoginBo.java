package com.jingyuu.ershoujing.dao.mybatis.bo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginBo extends BaseBean {
    // 手机号
    private String telephone;

    // 密码
    private String password;

    // 验证码
    private String code;

    // IP
    private String ip;
}
