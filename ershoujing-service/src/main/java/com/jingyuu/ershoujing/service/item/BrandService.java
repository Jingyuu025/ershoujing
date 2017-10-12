package com.jingyuu.ershoujing.service.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.item.BrandEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.BrandBo;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
public interface BrandService {
    /**
     * 创建品牌
     *
     * @param brandBo
     */
    void save(BrandBo brandBo) throws JyuException;

    /**
     * 查询品牌列表
     *
     * @return
     */
    List<BrandEntity> listBrand();
}
