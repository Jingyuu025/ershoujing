package com.jingyuu.ershoujing.service.system.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.PeopleEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.PeopleRepository;
import com.jingyuu.ershoujing.service.system.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author owen
 * @date 2017-09-14
 */
@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    @Transactional(readOnly = true)
    public PeopleEntity get(String userId) {
        return peopleRepository.findOne(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public PeopleEntity load(String userId) throws JyuException {
        PeopleEntity peopleEntity = this.get(userId);
        if (CommonUtil.isEmpty(peopleEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "人员不存在,用户编号:" + userId);
        }
        return peopleEntity;
    }
}
