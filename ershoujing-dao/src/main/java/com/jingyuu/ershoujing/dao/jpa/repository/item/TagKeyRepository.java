package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.BrandEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface BrandRepository extends BaseRepository<BrandEntity, Long> {
    /**
     * 查询品牌列表
     *
     * @param bName 品牌名称
     * @return
     */
    List<BrandEntity> findByBNameLike(String bName);
}
