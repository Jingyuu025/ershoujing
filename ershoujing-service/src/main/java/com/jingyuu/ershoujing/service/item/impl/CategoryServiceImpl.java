package com.jingyuu.ershoujing.service.item.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.item.CategoryEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.item.CategoryRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.CategoryBo;
import com.jingyuu.ershoujing.service.item.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void save(CategoryBo categoryBo) throws JyuException {
        String cName = categoryBo.getCategoryName(); // 类目名称
        List<CategoryEntity> categoryList = categoryRepository.findByCNameLike(cName);
        if (CommonUtil.isNotEmpty(categoryList)) {
            throw new JyuException(ErrorEnum.DATA_REPEAT_ERROR, "类目已存在! 类目名称:" + cName);
        }

        // 新增类目
        categoryRepository.save(
                CategoryEntity.builder()
                        .cName(cName)
                        .build()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<CategoryEntity> listCategory() {
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
