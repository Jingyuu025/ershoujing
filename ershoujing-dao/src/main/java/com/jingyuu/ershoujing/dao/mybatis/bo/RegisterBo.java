package com.jingyuu.ershoujing.dao.mybatis.bo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.RoleEnum;
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
public class RegisterBo extends BaseBean implements Check {
    // 手机号
    private String telephone;

    // 密码
    private String password;

    // 角色
    private RoleEnum roleEnum;

    // 注册IP
    private String ip;

    @Override
    public void checkParam() throws JyuException {

    }
}
