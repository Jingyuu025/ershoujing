package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.ItemTemplateEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface ItemTemplateRepository extends BaseRepository<ItemTemplateEntity, String> {

    /**
     * 查询商品模板列表
     *
     * @param brandId    品牌编号
     * @param categoryId 类目编号
     * @param itemName   商品模板名称
     */
    List<ItemTemplateEntity> findByBrandIdAndCategoryIdAndItemNameLike(long brandId, long categoryId, String itemName);
}
