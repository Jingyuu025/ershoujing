package com.jingyuu.ershoujing.dao.jpa.repository.system;

import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface FileRepository extends BaseRepository<FileEntity, String> {
    /**
     * 查询文件信息
     *
     * @param fileMd5
     * @return
     */
    List<FileEntity> findByFileMd5(String fileMd5);
}
