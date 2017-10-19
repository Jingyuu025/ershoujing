package com.jingyuu.ershoujing.dao.jpa.entity.item;

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
 * @date 2017-10-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_tag_value")
public class TagValueEntity extends BaseIdentityEntity {
    @Column(name = "key_id", columnDefinition = "INTEGER COMMENT '键编号'")
    private Long keyId;

    @Column(name = "key_value", columnDefinition = "VARCHAR(64) COMMENT '键文本'")
    private String keyText;

    @Column(name = "value_text", columnDefinition = "VARCHAR(64) COMMENT '键值'")
    private String valueText;

    @Column(name = "value_type", columnDefinition = "SMALLINT(6) COMMENT ', 1：短标签 2：长标签'")
    private Integer valueType;

    @Column(name = "tip_text", columnDefinition = "VARCHAR(512) COMMENT '键提示文本'")
    private String tipText;

    @Column(name = "tip_fid", columnDefinition = "VARCHAR(64) COMMENT '键提示图片'")
    private String tipFid;
}
