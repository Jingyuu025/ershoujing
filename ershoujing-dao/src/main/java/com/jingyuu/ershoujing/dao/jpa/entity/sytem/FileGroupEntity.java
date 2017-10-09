package com.jingyuu.ershoujing.dao.jpa.entity.sytem;

import com.jingyuu.ershoujing.dao.base.BaseCustomIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author owen
 * @date 2017-09-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_file_group")
public class FileGroupEntity extends BaseCustomIdEntity {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(64) COMMENT '图片组编号'")
    private String id;
}
