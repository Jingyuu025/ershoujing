package com.jingyuu.ershoujing.dao.jpa.entity.user;

import com.jingyuu.ershoujing.dao.base.BaseCustomIdEntity;
import com.jingyuu.ershoujing.dao.base.BaseIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户会话
 *
 * @author owen
 * @date 2017-09-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_user_session")
public class UserSessionEntity extends BaseCustomIdEntity {
    @Id
    private String userId;

    @Column(name = "nick_name", columnDefinition = "VARCHAR(32) COMMENT '用户昵称'")
    private String nickName;

    @Column(name = "telephone", columnDefinition = "VARCHAR(16) COMMENT '手机号'")
    private String telephone;

    @Column(name = "access_token", columnDefinition = "VARCHAR(64) COMMENT '访问令牌'")
    private String accessToken;

    @Column(name = "grant_time", columnDefinition = "DATETIME COMMENT '令牌颁发时间'")
    private Date grantTime;

    @Column(name = "expire_in", columnDefinition = "DATETIME COMMENT '访问令牌超时时间'")
    private Date expireIn;

    @Column(name = "refresh_token", columnDefinition = "VARCHAR(64) COMMENT '刷新令牌'")
    private String refreshToken;

    @Column(name = "refresh_expire_in", columnDefinition = "DATETIME COMMENT '刷新令牌超时时间'")
    private Date refreshExpireIn;
}
