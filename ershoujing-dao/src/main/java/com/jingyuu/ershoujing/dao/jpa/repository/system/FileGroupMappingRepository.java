package com.jingyuu.ershoujing.dao.jpa.repository.system;

import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileGroupMappingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface FileGroupMappingRepository extends CrudRepository<FileGroupMappingEntity, Long> {
    /**
     * 查询文件组列表
     *
     * @param groupId 文件组编号
     * @return
     */
    List<FileGroupMappingEntity> findByGroupId(String groupId);
}
