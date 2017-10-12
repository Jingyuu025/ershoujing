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
@Table(name = "jyu_tag_key")
public class TagKeyEntity extends BaseIdentityEntity {
    @Column(name = "type", columnDefinition = "SMALLINT(6) COMMENT '标签键类型 1：基本信息 2：外观成色 3：功能性问题'")
    private Integer type;

    @Column(name ="key_text",columnDefinition = "VARCHAR(64) COMMENT '键'")
    private String keyText;

    @Column(name ="tip_text",columnDefinition = "VARCHAR(512) COMMENT '键提示文本'")
    private String tipText;

    @Column(name ="tip_fid",columnDefinition = "VARCHAR(64) COMMENT '键提示图片'")
    private String tipFid;
}
