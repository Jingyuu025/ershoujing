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
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBo extends BaseBean {
    /**
     * 手机号
     */
    private String telephone;

    /**
     * 密码
     * 使用transient关键字，指定GSON解析时过滤该字段
     */
    private transient String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 注册IP
     */
    private String ip;
}
