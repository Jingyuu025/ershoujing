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
 * @date 2017-10-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_brand")
public class BrandEntity extends BaseIdentityEntity {
    @Column(name = "b_name", columnDefinition = "VARCHAR(32) COMMENT '品牌名称'")
    private String bName;

    @Column(name = "logo_fid", columnDefinition = "VARCHAR(64) COMMENT 'logo图片编号'")
    private String logoFid;
}
