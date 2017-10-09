package com.jingyuu.ershoujing.dao.jpa.repository.system;

import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileGroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface FileGroupRepository extends CrudRepository<FileGroupEntity, String> {
}
