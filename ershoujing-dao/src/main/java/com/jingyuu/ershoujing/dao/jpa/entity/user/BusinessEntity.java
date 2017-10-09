package com.jingyuu.ershoujing.dao.jpa.entity.user;

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
 * 商户
 *
 * @author owen
 * @date 2017-09-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_business")
public class BusinessEntity extends BaseCustomIdEntity {
    @Id
    private String userId;

    @Column(name = "company", columnDefinition = "VARCHAR(128) COMMENT '公司名称'")
    private String company;


    @Column(name = "finish_order_number", columnDefinition = "INT COMMENT '成交订单数目'")
    private Integer finishOrderNumber;
}
