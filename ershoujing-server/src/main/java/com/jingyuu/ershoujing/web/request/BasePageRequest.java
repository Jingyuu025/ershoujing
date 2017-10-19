package com.jingyuu.ershoujing.web.request;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页请求参数
 *
 * startIndex，开始索引（从0开始），举例：总记录数32，每页展示10条记录
 * ----------------------------------------------------------
 *            |    startIndex   |      pageSize/actualSize
 * ----------------------------------------------------------
 * 第一页      |       0         |         10/10
 * ----------------------------------------------------------
 * 第二页      |       10        |         10/10
 * ----------------------------------------------------------
 * 第三页      |       20        |         10/10
 * ----------------------------------------------------------
 * 第四页      |       30        |         10/2
 * ----------------------------------------------------------
 *
 * @author owen
 * @date 2017-09-07
 */
@Data
public class BasePageRequest extends BaseBean {
    // 开始索引，默认0
    protected Integer startIndex = 0;

    // 每页数量，默认10
    protected Integer pageSize = 10;
}