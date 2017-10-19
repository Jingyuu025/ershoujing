package com.jingyuu.ershoujing.dao.jpa.entity.item;

import com.jingyuu.ershoujing.dao.base.BaseCustomIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author owen
 * @date 2017-10-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_item_template")
public class ItemTemplateEntity extends BaseCustomIdEntity {
    @Id
    @GeneratedValue(generator = "pk_gen")
    @GenericGenerator(name = "pk_gen",
            strategy = "com.jingyuu.ershoujing.dao.base.id.ItemTemplateIdGenerator")
    private String id;

    @Column(name = "category_id", columnDefinition = "SMALLINT(6) COMMENT '类目编号'")
    private Long categoryId;

    @Column(name = "category_name", columnDefinition = "VARCHAR(64) COMMENT '类目名称'")
    private String categoryName;

    @Column(name = "brand_id", columnDefinition = "SMALLINT(6) COMMENT '品牌编号'")
    private Long brandId;

    @Column(name = "brand_name", columnDefinition = "VARCHAR(64) COMMENT '品牌名称'")
    private String brandName;

    @Column(name = "item_name", columnDefinition = "VARCHAR(128) COMMENT '商品名称'")
    private String itemName;

    @Column(name = "hot_sell", columnDefinition = "SMALLINT(6) COMMENT '是否热销 1：非热销 2：热销'")
    private Integer hotSell;

    @Column(name = "deal_number", columnDefinition = "INTEGER COMMENT '回收次数'")
    private Integer dealNumber;

    @Column(name = "highest_price", columnDefinition = "DECIMAL(9,2) COMMENT '回收最高价格'")
    private BigDecimal highestPrice;

    @Column(name = "state", columnDefinition = "SMALLINT(6) COMMENT '状态 1：上架 2：下架'")
    private Integer state;
}
