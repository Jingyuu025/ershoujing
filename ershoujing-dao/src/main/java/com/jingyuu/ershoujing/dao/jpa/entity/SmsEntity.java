package com.jingyuu.ershoujing.dao.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author owen
 * @date 2017-09-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_message_sms")
public class SmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "telephone", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '手机号'")
    private String telephone;

    @Column(name = "business_type", columnDefinition = "INT NOT NULL COMMENT '业务类型 (1: 经销商微信账号绑定)'")
    private Integer businessType;

    @Column(name = "code", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '验证码'")
    private String code;

    @Column(name = "add_time", columnDefinition = "DATETIME NOT NULL COMMENT '添加时间'")
    private Date addTime;

    @Column(name = "validity", columnDefinition = "INT NOT NULL COMMENT '有效期,单位为分钟'")
    private Integer validity;

    @Column(name = "verify_status", columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '验证状态 1:待验证 2:已验证'")
    private Integer verifyStatus;
}

