package com.jingyuu.ershoujing.dao.base;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

/**
 * 自增主键实体超类
 *
 * @author owen
 * @date 2017-08-10
 */
@Data
@MappedSuperclass
public class BaseIdentityEntity extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "add_time", columnDefinition = "DATETIME COMMENT '新增时间'")
    private Date addTime = Date.from(Instant.now());
}
