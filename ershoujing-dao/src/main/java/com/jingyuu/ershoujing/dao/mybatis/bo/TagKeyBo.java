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
public class BrandBo extends BaseBean {
    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌图片
     */
    private byte[] data;
}
