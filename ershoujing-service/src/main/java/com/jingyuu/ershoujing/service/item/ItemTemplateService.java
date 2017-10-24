package com.jingyuu.ershoujing.service.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.item.ItemTemplateEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.ItemTemplateBo;

/**
 * @author owen
 * @date 2017-10-19
 */
public interface ItemTemplateService {

    /**
     * 新增商品模板
     *
     * @param itemTemplateBo 商品模板Bo
     * @throws JyuException
     */
    ItemTemplateEntity save(ItemTemplateBo itemTemplateBo) throws JyuException;

    /**
     * 查询商品模板详情
     *
     * @param itemTemplateId 商品模板编号
     * @return
     */
    ItemTemplateEntity get(String itemTemplateId);

    /**
     * 查询商品模板详情
     *
     * @param itemTemplateId 商品模板编号
     * @return
     * @throws JyuException
     */
    ItemTemplateEntity load(String itemTemplateId) throws JyuException;

    /**
     * 设置热销商品模板
     *
     * @param itemTemplateId 商品模板编号
     * @throws JyuException
     */
    void setHotSell(String itemTemplateId) throws JyuException;

    /**
     * 移除热销商品模板
     *
     * @param itemTemplateId 商品模板编号
     * @throws JyuException
     */
    void removeHotSell(String itemTemplateId) throws JyuException;

    /**
     * 上架商品模板
     *
     * @param itemTemplateId 商品模板编号
     * @throws JyuException
     */
    void onShelf(String itemTemplateId) throws JyuException;

    /**
     * 下架商品模板
     *
     * @param itemTemplateId 商品模板编号
     * @throws JyuException
     */
    void offShelf(String itemTemplateId) throws JyuException;

}
