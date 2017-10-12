package com.jingyuu.ershoujing.service.item.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagKeyEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagValueEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.item.TagKeyRepository;
import com.jingyuu.ershoujing.dao.jpa.repository.item.TagValueRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueBo;
import com.jingyuu.ershoujing.service.item.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-10
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagKeyRepository keyRepository;
    @Autowired
    private TagValueRepository valueRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void saveTagKey(TagKeyBo tagKeyBo) throws JyuException {
        String key = tagKeyBo.getKey();

        List<TagKeyEntity> tagKeyList = keyRepository.findByKeyTextLike(key);
//        if()

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
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void saveTagValue(TagValueBo tagValueBo) throws JyuException {

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
    public List<TagValueEntity> listTagValue(Long tagKeyId) {
        return valueRepository.findByKeyId(tagKeyId);
    }
}
