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
public class Category extends BaseBean {
    /**
     * 类目编号
     */
    private Long cId;

    /**
     * 类目名称
     */
    private String cName;

}
