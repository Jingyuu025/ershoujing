package com.jingyuu.ershoujing.dao.jpa.entity;

import com.jingyuu.ershoujing.dao.base.BaseIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 短信验证码
 *
 * @author owen
 * @date 2017-09-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_sms_code")
public class SmsCodeEntity extends BaseIdentityEntity {
    @Column(name = "telephone", columnDefinition = "VARCHAR(16) NOT NULL COMMENT '手机号'")
    private String telephone;

    @Column(name = "business_type", columnDefinition = "INT NOT NULL COMMENT '业务类型 (1: 注册 2：登录)'")
    private Integer businessType;

    @Column(name = "serial_number", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '序列号'")
    private String serialNumber;

    @Column(name = "code", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '验证码'")
    private String code;

    @Column(name = "expire_in", columnDefinition = "INT NOT NULL COMMENT '有效期,单位为秒'")
    private Integer expireIn;

    @Column(name = "expire_time", columnDefinition = "DATETIME NOT NULL COMMENT '过期时间'")
    private Date expireTime;

    @Column(name = "state", columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '验证状态 1:待验证 2:已验证'")
    private Integer state;
}
