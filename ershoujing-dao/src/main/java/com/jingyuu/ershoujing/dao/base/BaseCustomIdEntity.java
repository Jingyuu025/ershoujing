package com.jingyuu.ershoujing.dao.base;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.Date;

/**
 * 自定义主键实体超类
 *
 * @author owen
 * @date 2017-08-10
 */
@MappedSuperclass
@Data
public class BaseCustomIdEntity extends BaseBean {
    @Column(name = "add_time", columnDefinition = "DATETIME COMMENT '新增时间'")
    private Date addTime = Date.from(Instant.now());
}
