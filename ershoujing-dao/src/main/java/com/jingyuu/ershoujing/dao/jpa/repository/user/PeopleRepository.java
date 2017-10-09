package com.jingyuu.ershoujing.dao.jpa.repository.user;

import com.jingyuu.ershoujing.dao.jpa.entity.user.PeopleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface PeopleRepository extends CrudRepository<PeopleEntity, String> {
}
