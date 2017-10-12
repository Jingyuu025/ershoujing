package com.jingyuu.ershoujing.dao.mybatis.bo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.statics.enums.TagKeyTypeEnum;
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
     * 健类型
     */
    private TagKeyTypeEnum keyTypeEnum;

    /**
     * 健
     */
    private String key;

    /**
     * 键提示文本
     */
    private String tipText;


    /**
     * 品牌图片
     */
    private byte[] data;
}
