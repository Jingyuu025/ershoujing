package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.TagKeyEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface TagKeyRepository extends BaseRepository<TagKeyEntity, Long> {
    /**
     * 查询标签健列表
     *
     * @param key 标签健
     * @return
     */
    List<TagKeyEntity> findByKeyTextLike(String key);
}
