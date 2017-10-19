package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.TagValueEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface TagValueRepository extends BaseRepository<TagValueEntity, Long> {
    /**
     * 查询标签值列表
     *
     * @param keyId
     * @return
     */
    List<TagValueEntity> findByKeyId(Long keyId);

    /**
     * 查询标签值列表
     *
     * @param keyId 标签健编号
     * @param value 标签值
     * @return
     */
    List<TagValueEntity> findByKeyIdAndValueTextLike(Long keyId, String value);
}
