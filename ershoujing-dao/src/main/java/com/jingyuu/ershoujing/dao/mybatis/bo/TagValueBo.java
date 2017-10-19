package com.jingyuu.ershoujing.dao.mybatis.bo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.statics.enums.TagValueTypeEnum;
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
public class TagValueBo extends BaseBean {
    /**
     * 标签健编号
     */
    private Long keyId;

    /**
     * 标签值
     */
    private String value;

    /**
     * 标签值类型
     */
    private TagValueTypeEnum valueTypeEnum;

    /**
     * 值提示文本
     */
    private String tipText;

    /**
     * 值提示图片编号
     */
    private String tipFid;
}
