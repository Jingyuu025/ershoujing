package com.jingyuu.ershoujing.web.domain;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-10-06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseBean {
    /**
     * 品牌编号
     */
    private Long bId;

    /**
     * 品牌名称
     */
    private String bName;

    /**
     * 品牌图片文件编号
     */
    private String logoFid;

}
