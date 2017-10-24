package com.jingyuu.ershoujing.web.controller.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.dao.jpa.entity.item.ItemTemplateEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.ItemTemplateBo;
import com.jingyuu.ershoujing.service.item.ItemTemplateService;
import com.jingyuu.ershoujing.service.support.annotation.Log;
import com.jingyuu.ershoujing.web.controller.BaseController;
import com.jingyuu.ershoujing.web.request.ItemTemplateRequest;
import com.jingyuu.ershoujing.web.response.BaseResp;
import com.jingyuu.ershoujing.web.response.ItemTemplateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author owen
 * @date 2017-10-16
 */
@Api(tags = "商品模板")
@RestController
@RequestMapping(value = "/item-template")
public class ItemTemplateController extends BaseController {
    @Autowired
    private ItemTemplateService itemTemplateService;

    @Log("新增商品模板")
    @ApiOperation(value = "新增商品模板")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<ItemTemplateResult>> save(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @RequestBody ItemTemplateRequest itemTemplateRequest)
            throws JyuException {
        ItemTemplateEntity itemTemplateEntity = itemTemplateService.save(
                ItemTemplateBo.builder()
                        .brandId(itemTemplateRequest.getBrandId())
                        .categoryId(itemTemplateRequest.getCategoryId())
                        .itemName(itemTemplateRequest.getItemName())
                        .itemFid(itemTemplateRequest.getItemFid())
                        .build()
        );
        return ResponseEntity.ok(BaseResp.ok(itemTemplateEntity.toBean(ItemTemplateResult.class)));
    }

    @Log("设置热销商品模板")
    @ApiOperation(value = "设置热销商品模板")
    @RequestMapping(value = "/hot-sell/set/{itemTemplateId}", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<ItemTemplateResult>> setHotSell(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @PathVariable String itemTemplateId)
            throws JyuException {
        itemTemplateService.setHotSell(itemTemplateId);
        return ResponseEntity.ok(BaseResp.ok());
    }

    @Log("移除热销商品模板")
    @ApiOperation(value = "移除热销商品模板")
    @RequestMapping(value = "/hot-sell/remove/{itemTemplateId}", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<ItemTemplateResult>> removeHotSell(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @PathVariable String itemTemplateId)
            throws JyuException {
        itemTemplateService.removeHotSell(itemTemplateId);
        return ResponseEntity.ok(BaseResp.ok());
    }

    @Log("上架商品模板")
    @ApiOperation(value = "上架商品模板")
    @RequestMapping(value = "/on-shelf/{itemTemplateId}", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<ItemTemplateResult>> onShelf(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @PathVariable String itemTemplateId)
            throws JyuException {
        itemTemplateService.onShelf(itemTemplateId);
        return ResponseEntity.ok(BaseResp.ok());
    }

    @Log("下架商品模板")
    @ApiOperation(value = "下架商品模板")
    @RequestMapping(value = "/off-shelf/{itemTemplateId}", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<ItemTemplateResult>> offShelf(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @PathVariable String itemTemplateId)
            throws JyuException {
        itemTemplateService.offShelf(itemTemplateId);
        return ResponseEntity.ok(BaseResp.ok());
    }

}
