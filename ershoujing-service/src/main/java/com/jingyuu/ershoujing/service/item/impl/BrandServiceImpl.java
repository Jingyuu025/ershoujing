package com.jingyuu.ershoujing.service.item.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.item.BrandEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.item.BrandRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.BrandBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.FileBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.FileVo;
import com.jingyuu.ershoujing.service.item.BrandService;
import com.jingyuu.ershoujing.service.system.FileService;
import com.jingyuu.ershoujing.service.system.impl.FileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private FileService fileService;
    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private BrandRepository brandRepository;

    /**
     * 创建品牌
     *
     * @param brandBo
     */
    public void save(BrandBo brandBo) throws JyuException {
        String bName = brandBo.getBrandName();
        byte[] data = brandBo.getData();

        List<BrandEntity> brandList = brandRepository.findByBNameLike(bName);
        if (CommonUtil.isNotEmpty(brandList)) {
            throw new JyuException(ErrorEnum.DATA_REPEAT_ERROR, "品牌已存在，品牌名称:" + bName);
        }

        // 构造Brand
        BrandEntity brand = BrandEntity.builder()
                .bName(bName)
                .build();
        // 品牌图片处理
        if (CommonUtil.isNotEmpty(data) && data.length > 0) {
            // 上传品牌图片
            FileVo fileVo = fileService.saveFile(
                    FileBo.builder()
                            .fileName(bName)
                            .bucketName(fileConfig.getOss().getBucket().getJyuBucket()) // 鲸鱼优专用bucket
                            .data(data)
                            .build()
            );

            // 设置品牌图片编号
            if (CommonUtil.isNotEmpty(fileVo)) {
                brand.setLogoFid(fileVo.getFileId());
            }
        }

        // 保存品牌信息
        brandRepository.save(brand);
    }

    /**
     * 查询品牌列表
     *
     * @return
     */
    public List<BrandEntity> listBrand() {
        List<BrandEntity> brandList = brandRepository.findAll();
        return brandList;
    }
}
