package com.jingyuu.ershoujing.common.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页查询条件
 *
 * @author owen
 * @date 2017-08-29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery<T> implements Serializable {
    // 起始记录索引
    private int startIndex;

    // 每页展示记录数
    private int pageSize;

    // 查询条件
    private T conditions;
}
