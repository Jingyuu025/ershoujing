package com.jingyuu.ershoujing.web.domain;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.statics.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author owen
 * @date 2017-09-14
 */
@Data
@Builder
public class User extends BaseBean {
    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 角色
     */
    private RoleEnum role;
}
