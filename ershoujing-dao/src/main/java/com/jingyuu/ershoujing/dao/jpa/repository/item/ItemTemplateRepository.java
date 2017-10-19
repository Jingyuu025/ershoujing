package com.jingyuu.ershoujing.dao.jpa.repository.item;

import com.jingyuu.ershoujing.dao.jpa.entity.item.ItemTemplateEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagValueEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Repository
public interface ItemTemplateRepository extends BaseRepository<ItemTemplateEntity, String> {
}