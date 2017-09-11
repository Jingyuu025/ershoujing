package com.jingyuu.ershoujing.dao.jpa.entity;

import com.jingyuu.ershoujing.dao.base.BaseCustomIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author owen
 * @date 2017-09-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_user")
public class UserEntity extends BaseCustomIdEntity {
    @Id
    @GeneratedValue(generator = "pk_gen")
    @GenericGenerator(name = "pk_gen",
            strategy = "com.jingyuu.ershoujing.dao.base.id.UserIdGenerator")
    private String id;

    @Column(name = "telephone", columnDefinition = "VARCHAR(16) COMMENT '手机号'")
    private String telephone;

    @Column(name = "password", columnDefinition = "VARCHAR(32) COMMENT '密码'")
    private String password;

    @Column(name = "salt", columnDefinition = "VARCHAR(32) COMMENT '盐'")
    private String salt;

    @Column(name = "role", columnDefinition = "SMALLINT(6) COMMENT '角色, 1:客户, 2:商户, 3:物流师傅, 4:库管专员 5:客服专员, 6:系统管理员'")
    private Integer role;

    @Column(name = "state", columnDefinition = "SMALLINT(6) COMMENT '状态, 1:可用 2:锁定 3:禁用'")
    private Integer state;

    @Column(name = "last_login_ip", columnDefinition = "VARCHAR(32) COMMENT '最近登录时间'")
    private String lastLoginIp;

    @Column(name = "last_login_time", columnDefinition = "DATETIME COMMENT '最近登录时间'")
    private Date lastLoginTime;
}
