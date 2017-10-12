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
@Table(name = "jyu_category")
public class CategoryEntity extends BaseIdentityEntity {
    @Column(name = "b_name", columnDefinition = "VARCHAR(32) COMMENT '类目名称'")
    private String cName;
}
