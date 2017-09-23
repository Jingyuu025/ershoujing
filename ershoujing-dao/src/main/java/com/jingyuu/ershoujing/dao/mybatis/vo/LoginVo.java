package com.jingyuu.ershoujing.dao.mybatis.vo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.statics.enums.RoleEnum;
import com.jingyuu.ershoujing.common.statics.enums.TerminalEnum;
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
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo extends BaseBean {
    /**
     * 终端
     */
    private TerminalEnum terminal;

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

    /**
     * 令牌
     */
    private String token;
}
