package com.jingyuu.ershoujing.dao.jpa.entity;

import com.jingyuu.ershoujing.dao.base.BaseIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author owen
 * @date 2017-09-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_log")
public class LogEntity extends BaseIdentityEntity {
    @Column(name = "user_id", columnDefinition = "VARCHAR(32) COMMENT '用户编号'")
    private String userId;

    @Column(name = "nick_name", columnDefinition = "VARCHAR(32) COMMENT '用户昵称'")
    private String nickName;

    @Column(name = "telephone", columnDefinition = "VARCHAR(16) COMMENT '手机号'")
    private String telephone;

    @Column(name = "action", columnDefinition = "VARCHAR(128) COMMENT '行为'")
    private String action;

    @Column(name = "path", columnDefinition = "VARCHAR(128) COMMENT '请求路径'")
    private String path;

    @Column(name = "parameter", columnDefinition = "VARCHAR(512) COMMENT '请求参数'")
    private String parameter;

    @Column(name = "ip", columnDefinition = "VARCHAR(16) COMMENT '请求IP'")
    private String ip;
}
