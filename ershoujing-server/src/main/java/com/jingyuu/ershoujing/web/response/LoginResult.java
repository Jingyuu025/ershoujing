package com.jingyuu.ershoujing.web.response;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.statics.enums.TerminalEnum;
import com.jingyuu.ershoujing.web.domain.User;
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
public class LoginResult extends BaseBean {
    /**
     * 终端
     */
    private TerminalEnum terminal;

    /**
     * 用户信息
     */
    private User user;

    /**
     * 令牌
     */
    private String token;
}
