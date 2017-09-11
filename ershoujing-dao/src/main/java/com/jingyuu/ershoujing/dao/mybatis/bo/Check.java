package com.jingyuu.ershoujing.dao.mybatis.bo;

import com.jingyuu.ershoujing.common.exception.JyuException;

/**
 * @author owen
 * @date 2017-09-08
 */
public interface Check {

    /**
     * 参数检查
     *
     * @throws JyuException
     */
    void checkParam() throws JyuException;
}
