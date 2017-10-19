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
public class TagKeyVo extends BaseBean {
    /**
     * 健编号
     */
    private Long id;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 健
     */
    private String keyText;

    /**
     * 提示文本
     */
    private String tipText;

    /**
     * 提示图片编号
     */
    private String tipFid;
}
