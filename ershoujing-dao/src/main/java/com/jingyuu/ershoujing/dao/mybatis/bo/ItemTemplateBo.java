package com.jingyuu.ershoujing.dao.mybatis.bo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-10-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemTemplateBo extends BaseBean {
    /**
     * 类目编号
     */
    private Long categoryId;

    /**
     * 品牌编号
     */
    private Long brandId;

    /**
     * 商品图片名称
     */
    private String itemName;

    /**
     * 商品模板图片编号
     */
    private String itemFid;
}
