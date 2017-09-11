package com.jingyuu.ershoujing.common.page.convert;


import com.jingyuu.ershoujing.common.base.BaseBean;
import com.jingyuu.ershoujing.common.page.PageQuery;

/**
 * @author owen
 * @date 2017-08-30
 */
public class PageQueryConvert {

    public static <M extends BaseBean, N> PageQuery<N> convert(PageQuery<M> mPageQuery, Class<N> nClazz) {
        // 查询条件
        M conditions = mPageQuery.getConditions();
        return PageQuery.<N>builder()
                .startIndex(mPageQuery.getStartIndex())
                .pageSize(mPageQuery.getPageSize())
                .conditions(conditions.fromBean(conditions, nClazz))
                .build();
    }
}




