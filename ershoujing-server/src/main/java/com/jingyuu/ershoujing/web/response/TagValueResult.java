package com.jingyuu.ershoujing.web.response;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.statics.enums.TagValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagValueResult extends BaseBean {
    /**
     * 健类型
     */
    private Integer type;

    /**
     * 健标号
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
     * 短值|长值
     */
    private TagValueTypeEnum valueType;

    /**
     * 提示文本
     */
    private String tipText;

    /**
     * 提示图片编号
     */
    private String tipFid;
}
