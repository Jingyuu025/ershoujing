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
 * 短信
 *
 * @author owen
 * @date 2017-09-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_sms")
public class SmsEntity extends BaseIdentityEntity {
    @Column(name = "telephone", columnDefinition = "VARCHAR(16) NOT NULL COMMENT '手机号'")
    private String telephone;

    @Column(name = "content", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '内容'")
    private String content;

    @Column(name = "state", columnDefinition = "SMALLINT(6) NOT NULL COMMENT '状态 1：发送处理中 2：发送成功 3：发送失败'")
    private Integer state;
}
