package com.jingyuu.ershoujing.service.user;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.user.PeopleEntity;

/**
 * @author owen
 * @date 2017-09-14
 */
public interface PeopleService {

    /**
     * 查询人员详情
     *
     * @param userId 用户编号
     * @return
     */
    PeopleEntity get(String userId);

    /**
     * 查询人员详情
     *
     * @param userId 用户编号
     * @return
     * @throws JyuException
     */
    PeopleEntity load(String userId) throws JyuException;
}
