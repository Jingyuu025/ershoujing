package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.BrandEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface TagKeyRepository extends BaseRepository<BrandEntity, Long> {
    /**
     * 查询品牌列表
     *
     * @param bName 品牌名称
     * @return
     */
    List<BrandEntity> findByBNameLike(String bName);
}
