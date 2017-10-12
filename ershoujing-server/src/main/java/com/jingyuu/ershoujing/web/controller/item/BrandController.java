package com.jingyuu.ershoujing.web.controller.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.item.BrandEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.BrandBo;
import com.jingyuu.ershoujing.service.item.BrandService;
import com.jingyuu.ershoujing.service.support.annotation.Log;
import com.jingyuu.ershoujing.web.controller.BaseController;
import com.jingyuu.ershoujing.web.domain.Brand;
import com.jingyuu.ershoujing.web.response.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author owen
 * @date 2017-10-09
 */
@Api(tags = "商品品牌")
@RestController
@RequestMapping(value = "/brand")
public class BrandController extends BaseController {
    @Autowired
    private BrandService brandService;

    /**
     * 新增品牌
     */
    @Log("新增品牌")
    @ApiOperation(value = "新增品牌")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<BaseResp> save(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token,
            @RequestBody(required = false) MultipartFile file, @RequestParam String brandName)
            throws JyuException, IOException {
        brandService.save(
                BrandBo.builder()
                        .brandName(brandName)
                        .data(CommonUtil.isNotEmpty(file) ? file.getBytes() : null)
                        .build()
        );
        return ResponseEntity.ok(BaseResp.ok());
    }

    /**
     * 查询品牌列表
     */
    @Log("查询品牌列表")
    @ApiOperation(value = "查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<BaseResp<List<Brand>>> list(@RequestHeader(JyuConstant.TOKEN_HEADER) String token) {
        List<BrandEntity> brandList = brandService.listBrand();
        return ResponseEntity.ok(
                BaseResp.ok(
                        Optional.ofNullable(brandList).orElse(Collections.emptyList())
                                .stream()
                                .map(brandEntity -> Brand.builder()
                                        .bId(brandEntity.getId())
                                        .bName(brandEntity.getBName())
                                        .logoFid(brandEntity.getLogoFid())
                                        .build())
                                .collect(Collectors.toList())
                ));
    }
}
