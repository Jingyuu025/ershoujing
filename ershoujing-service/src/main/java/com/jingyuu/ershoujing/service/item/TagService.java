package com.jingyuu.ershoujing.service.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagKeyEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagValueEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueBo;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-10
 */
public interface TagService {
    /**
     * 新增标签键
     *
     * @param tagKeyBo 标签健
     * @throws JyuException
     */
    void saveTagKey(TagKeyBo tagKeyBo) throws JyuException;

    /**
     * 获取标签健详情
     *
     * @param tagKeyId 标签健编号
     * @return
     */
    TagKeyEntity getTagKey(Long tagKeyId);

    /**
     * 获取标签健详情
     *
     * @param tagKeyId 标签健编号
     * @return
     * @throws JyuException
     */
    TagKeyEntity loadTagKey(Long tagKeyId) throws JyuException;

    /**
     * 新增标签值
     *
     * @param tagValueBo 标签值
     * @throws JyuException
     */
    void saveTagValue(TagValueBo tagValueBo) throws JyuException;

    /**
     * 获取标签值详情
     *
     * @param tagValueId 标签值编号
     * @return
     */
    TagValueEntity getTagValue(Long tagValueId);

    /**
     * 获取标签值详情
     *
     * @param tagValueId 标签值编号
     * @return
     * @throws JyuException
     */
    TagValueEntity loadTagValue(Long tagValueId) throws JyuException;

    /**
     * @param tagKeyId
     * @return
     */
    List<TagValueEntity> listTagValue(Long tagKeyId);

}
