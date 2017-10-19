package com.jingyuu.ershoujing.web.response;

import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.statics.enums.TerminalEnum;
import com.jingyuu.ershoujing.web.domain.User;
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
public class TagKeyResult extends BaseBean {
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
