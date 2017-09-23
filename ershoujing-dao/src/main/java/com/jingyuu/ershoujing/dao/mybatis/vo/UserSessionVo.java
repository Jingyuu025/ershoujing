package com.jingyuu.ershoujing.dao.mybatis.vo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author owen
 * @date 2017-09-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSessionVo extends BaseBean {
    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 令牌颁发时间
     */
    private Date grantTime;

    /**
     * 访问令牌超时时间
     */
    private Date expireIn;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 刷新令牌超时时间
     */
    private Date refreshExpireIn;
}
