package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
