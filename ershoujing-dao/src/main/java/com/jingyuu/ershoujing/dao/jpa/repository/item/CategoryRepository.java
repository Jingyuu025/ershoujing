package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.CategoryEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface CategoryRepository extends BaseRepository<CategoryEntity, Long> {
    /**
     * 查询类目列表
     *
     * @param cName 类目名称
     */
    List<CategoryEntity> findByCNameLike(String cName);
}
