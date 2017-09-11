package com.jingyuu.ershoujing.common.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author owen
 * @date 2017-08-29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<E> implements Serializable {
    // 总记录数
    private long totalCount;

    // 列表项
    private List<E> items;
}
