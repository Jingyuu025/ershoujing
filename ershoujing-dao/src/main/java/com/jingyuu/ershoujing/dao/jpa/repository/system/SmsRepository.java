package com.jingyuu.ershoujing.dao.jpa.repository.system;

import com.jingyuu.ershoujing.dao.jpa.entity.sytem.SmsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface SmsRepository extends CrudRepository<SmsEntity, Long> {
}
