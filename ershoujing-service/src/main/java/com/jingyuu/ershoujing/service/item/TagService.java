package com.jingyuu.ershoujing.service.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.page.PageBean;
import com.jingyuu.ershoujing.common.page.PageQuery;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagKeyEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagValueEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagKeyVo;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagValueVo;

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
    TagKeyEntity saveTagKey(TagKeyBo tagKeyBo) throws JyuException;

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
     * 查询标签健列表
     *
     * @param pageQuery 查询表单
     * @return
     * @throws JyuException
     */
    PageBean<TagKeyVo> listTagKey(PageQuery<TagKeyQueryBo> pageQuery) throws JyuException;

    /**
     * 新增标签值
     *
     * @param tagValueBo 标签值
     * @throws JyuException
     */
    TagValueEntity saveTagValue(TagValueBo tagValueBo) throws JyuException;


    /**
     * 批量新增标签值
     *
     * @param tagValueBoList 标签值列表
     * @return 返回新增失败的标签值
     */
    List<String> saveTagValueBatch(List<TagValueBo> tagValueBoList) throws JyuException;

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
     * 查询标签值列表
     *
     * @param pageQuery 查询表单
     * @return
     */
    PageBean<TagValueVo> listTagValue(PageQuery<TagValueQueryBo> pageQuery) throws JyuException;

}
