package com.jingyuu.ershoujing.dao.jpa.repository.user;

import com.jingyuu.ershoujing.dao.jpa.entity.user.BusinessEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface BusinessRepository extends BaseRepository<BusinessEntity, String> {
}
