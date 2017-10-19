package com.jingyuu.ershoujing.service.item.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.page.PageBean;
import com.jingyuu.ershoujing.common.page.PageQuery;
import com.jingyuu.ershoujing.common.page.convert.PageBeanConvert;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.statics.enums.TagKeyTypeEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagKeyEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagValueEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.item.TagKeyRepository;
import com.jingyuu.ershoujing.dao.jpa.repository.item.TagValueRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.mapper.TagMapper;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagKeyVo;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagValueVo;
import com.jingyuu.ershoujing.service.item.TagService;
import com.jingyuu.ershoujing.service.system.FileService;
import com.jingyuu.ershoujing.service.system.impl.FileConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author owen
 * @date 2017-10-10
 */
@Slf4j
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private FileService fileService;
    @Autowired
    private FileConfig fileConfig;
    @Autowired
    private TagKeyRepository keyRepository;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagValueRepository valueRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public TagKeyEntity saveTagKey(TagKeyBo tagKeyBo) throws JyuException {
        String key = tagKeyBo.getKey();

        // 标签健重复性校验
        List<TagKeyEntity> tagKeyList = keyRepository.findByKeyTextLike(key);
        if (CommonUtil.isNotEmpty(tagKeyList)) {
            throw new JyuException(ErrorEnum.DATA_REPEAT_ERROR, "标签健已存在，健名称:" + key);
        }

        TagKeyTypeEnum typeEnum = tagKeyBo.getKeyTypeEnum();
        String tipText = tagKeyBo.getTipText();
        String tipFid = tagKeyBo.getTipFid();

        // 构造标签健实体
        TagKeyEntity tagKeyEntity = TagKeyEntity.builder()
                .type(typeEnum.getValue())
                .keyText(key)
                .tipText(tipText)
                .tipFid(tipFid)
                .build();

        // 保存品牌信息
        return keyRepository.save(tagKeyEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public TagKeyEntity getTagKey(Long tagKeyId) {
        return keyRepository.findOne(tagKeyId);
    }

    @Override
    @Transactional(readOnly = true)
    public TagKeyEntity loadTagKey(Long tagKeyId) throws JyuException {
        TagKeyEntity keyEntity = this.getTagKey(tagKeyId);
        if (CommonUtil.isEmpty(keyEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "标签健不存在，标签健编号:" + tagKeyId);
        }
        return keyEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<TagKeyVo> listTagKey(PageQuery<TagKeyQueryBo> pageQuery) throws JyuException {
        TagKeyQueryBo tagKeyQueryBo = pageQuery.getConditions();
        List<TagKeyVo> tagKeyVoList = tagMapper.listTagKey(tagKeyQueryBo);
        return PageBeanConvert.convert(tagKeyVoList);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public TagValueEntity saveTagValue(TagValueBo tagValueBo) throws JyuException {
        long keyId = tagValueBo.getKeyId();
        String value = tagValueBo.getValue(); // 标签值

        // 查询标签健信息
        TagKeyEntity tagKeyEntity = this.loadTagKey(keyId);
        String keyText = tagKeyEntity.getKeyText();

        // 标签值重复性校验
        List<TagValueEntity> tagValueList = valueRepository.findByKeyIdAndValueTextLike(keyId, value);
        if (CommonUtil.isNotEmpty(tagValueList)) {
            throw new JyuException(ErrorEnum.DATA_REPEAT_ERROR, "标签值已存在，健名称:" + keyText + ",值名称:" + value);
        }

        String tipText = tagValueBo.getTipText();
        String tipFid = tagValueBo.getTipFid();

        // 保存品牌信息
        TagValueEntity tagValueEntity = TagValueEntity.builder()
                .keyId(keyId)
                .keyText(keyText)
                .valueText(value)
                .tipText(tipText)
                .tipFid(tipFid)
                .build();

        return valueRepository.save(tagValueEntity);
    }

    @Override
    public List<String> saveTagValueBatch(List<TagValueBo> tagValueBoList) throws JyuException {
        List<String> tagValueSaveErrorList = new ArrayList<>();
        if (CommonUtil.isNotEmpty(tagValueBoList)) {
            tagValueBoList.forEach(tagValueBo -> {
                String valueText = tagValueBo.getValue(); // 标签值
                try {
                    this.saveTagValue(tagValueBo);
                } catch (JyuException e) {
                    tagValueSaveErrorList.add(valueText + "-" + e.getErrMessage());
                    log.warn("批量新增标签值失败！标签值:{},异常信息:{}", valueText, e.getErrMessage());
                }
            });
        }

        return tagValueSaveErrorList;
    }

    @Override
    @Transactional(readOnly = true)
    public TagValueEntity getTagValue(Long tagValueId) {
        return valueRepository.findOne(tagValueId);
    }

    @Override
    @Transactional(readOnly = true)
    public TagValueEntity loadTagValue(Long tagValueId) throws JyuException {
        TagValueEntity tagValueEntity = this.getTagValue(tagValueId);
        if (CommonUtil.isEmpty(tagValueEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "标签值不存在，标签值编号:" + tagValueId);
        }
        return tagValueEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<TagValueVo> listTagValue(PageQuery<TagValueQueryBo> pageQuery) throws JyuException {
        TagValueQueryBo tagValueQueryBo = pageQuery.getConditions();
        List<TagValueVo> tagValueList = tagMapper.listTagValue(tagValueQueryBo);
        return PageBeanConvert.convert(tagValueList);
    }
}
