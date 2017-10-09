package com.jingyuu.ershoujing.dao.jpa.entity.sytem;

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
 * @date 2017-09-25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jyu_file_group_mapping")
public class FileGroupMappingEntity extends BaseIdentityEntity {
    @Column(name = "group_id", columnDefinition = "VARCHAR(8) COMMENT '文件组编号'")
    private String groupId;

    @Column(name = "file_id", columnDefinition = "VARCHAR(64) COMMENT '文件编号'")
    private String fileId;
}
