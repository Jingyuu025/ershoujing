package com.jingyuu.ershoujing.dao.jpa.repository;

import com.jingyuu.ershoujing.dao.jpa.entity.SmsCodeEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface SmsCodeRepository extends CrudRepository<SmsCodeEntity, Long> {


}
