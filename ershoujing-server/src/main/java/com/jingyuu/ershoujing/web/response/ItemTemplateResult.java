package com.jingyuu.ershoujing.web.response;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author owen
 * @date 2017-09-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemTemplateResult extends BaseBean {
    /**
     * 商品品牌名称
     */
    private String brandName;

    /**
     * 商品类目名称
     */
    private String categoryName;

    /**
     * 商品模板编号
     */
    private String id;

    /**
     * 商品模板名称
     */
    private String itemName;

    /**
     * 商品模板图片编号
     */
    private String itemFid;

    /**
     * 是否热销
     */
    private Integer hotSell;

    /**
     * 回收次数
     */
    private Integer dealNumber;

    /**
     * 回收最高价格
     */
    private BigDecimal highestPrice;
}
