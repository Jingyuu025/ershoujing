package com.jingyuu.ershoujing.common.page.convert;

import com.github.pagehelper.PageInfo;
import com.jingyuu.ershoujing.common.page.PageBean;

import java.util.List;

/**
 * @author owen
 * @date 2017-08-30
 */
public class PageBeanConvert<T> {
    /**
     * 分页转换
     *
     * @param tList
     * @return
     */
    public static <T> PageBean<T> convert(List<T> tList) {
        PageInfo<T> pageInfo = new PageInfo<>(tList);
        return PageBean.<T>builder()
                .totalCount(pageInfo.getTotal())
                .items(tList)
                .build();
    }
}
