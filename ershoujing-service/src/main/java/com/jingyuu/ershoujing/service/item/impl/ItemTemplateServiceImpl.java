package com.jingyuu.ershoujing.service.item.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.item.BrandEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.CategoryEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.ItemTemplateEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.item.ItemTemplateRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.ItemTemplateBo;
import com.jingyuu.ershoujing.service.item.BrandService;
import com.jingyuu.ershoujing.service.item.CategoryService;
import com.jingyuu.ershoujing.service.item.ItemTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author owen
 * @date 2017-10-19
 */
@Slf4j
@Service
public class ItemTemplateServiceImpl implements ItemTemplateService {
    // 是否热销
    public static final Integer HOT_SELL_NO = 1;
    public static final Integer HOT_SELL_YES = 2;

    // 上下架
    public static final Integer ON_SHELF = 1;
    public static final Integer OFF_SHELF = 2;

    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemTemplateRepository itemTemplateRepository;

    /**
     * 新增商品模板
     *
     * @param itemTemplateBo 商品模板Bo
     * @throws JyuException
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ItemTemplateEntity save(ItemTemplateBo itemTemplateBo) throws JyuException {
        long categoryId = itemTemplateBo.getCategoryId();
        long brandId = itemTemplateBo.getBrandId();

        // 查询类目信息
        CategoryEntity category = categoryService.load(categoryId);
        String categoryName = category.getCName();

        // 查询品牌信息
        BrandEntity brand = brandService.load(brandId);
        String brandName = brand.getBName();

        String itemName = itemTemplateBo.getItemName();

        // 商品模板重复性校验
        List<ItemTemplateEntity> itemTemplateEntityList =
                itemTemplateRepository.findByBrandIdAndCategoryIdAndItemNameLike(brandId, categoryId, itemName);
        if (CommonUtil.isNotEmpty(itemTemplateEntityList)) {
            throw new JyuException(ErrorEnum.DATA_REPEAT_ERROR, "商品模板已存在！商品模板名称:" + itemName);
        }

        // 新增商品模板
        ItemTemplateEntity itemTemplateEntity = itemTemplateRepository.save(
                ItemTemplateEntity.builder()
                        .brandId(brandId).brandName(brandName)
                        .categoryId(categoryId).categoryName(categoryName)
                        .itemName(itemName)
                        .dealNumber(JyuConstant.NUMBER_ZERO) // 回收次数
                        .highestPrice(BigDecimal.ZERO) // 回收最高价格
                        .hotSell(HOT_SELL_NO) //   非热销
                        .state(ON_SHELF) // 上架状态
                        .build()
        );

        return itemTemplateEntity;
    }


    @Override
    @Transactional(readOnly = true)
    public ItemTemplateEntity get(String itemTemplateId) {
        return itemTemplateRepository.findOne(itemTemplateId);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemTemplateEntity load(String itemTemplateId) throws JyuException {
        ItemTemplateEntity itemTemplateEntity = this.get(itemTemplateId);
        if (CommonUtil.isEmpty(itemTemplateEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "商品模板不存在! 商品模板编号:" + itemTemplateId);
        }
        return itemTemplateEntity;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void setHotSell(String itemTemplateId) throws JyuException {
        ItemTemplateEntity itemTemplateEntity = this.load(itemTemplateId);
        itemTemplateEntity.setHotSell(HOT_SELL_YES);
        itemTemplateRepository.save(itemTemplateEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void removeHotSell(String itemTemplateId) throws JyuException {
        ItemTemplateEntity itemTemplateEntity = this.load(itemTemplateId);
        itemTemplateEntity.setHotSell(HOT_SELL_NO);
        itemTemplateRepository.save(itemTemplateEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onShelf(String itemTemplateId) throws JyuException {
        ItemTemplateEntity itemTemplateEntity = this.load(itemTemplateId);
        itemTemplateEntity.setState(ON_SHELF);
        itemTemplateRepository.save(itemTemplateEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void offShelf(String itemTemplateId) throws JyuException {
        ItemTemplateEntity itemTemplateEntity = this.load(itemTemplateId);
        itemTemplateEntity.setState(OFF_SHELF);
        itemTemplateRepository.save(itemTemplateEntity);
    }


}
