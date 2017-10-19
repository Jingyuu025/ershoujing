package com.jingyuu.ershoujing.dao.mybatis.vo;

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
public class TagValueVo extends BaseBean {
    /**
     * 类型
     */
    private Integer type;

    /**
     * 健编号
     */
    private Long keyId;

    /**
     * 健文本
     */
    private String keyText;

    /**
     * 值编号
     */
    private Long valueId;

    /**
     * 值文本
     */
    private String valueText;

    /**
     * 值类型
     * 长值|短值
     */
    private Integer valueType;

    /**
     * 提示文本
     */
    private String tipText;

    /**
     * 提示图片编号
     */
    private String tipFid;
}
