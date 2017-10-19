package com.jingyuu.ershoujing.web.controller.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.page.PageBean;
import com.jingyuu.ershoujing.common.page.PageQuery;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.statics.enums.TagKeyTypeEnum;
import com.jingyuu.ershoujing.common.statics.enums.TagValueTypeEnum;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagKeyEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.item.TagValueEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagKeyVo;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagValueVo;
import com.jingyuu.ershoujing.service.item.TagService;
import com.jingyuu.ershoujing.service.support.annotation.Log;
import com.jingyuu.ershoujing.web.controller.BaseController;
import com.jingyuu.ershoujing.web.request.TagKeyQueryRequest;
import com.jingyuu.ershoujing.web.request.TagKeyRequest;
import com.jingyuu.ershoujing.web.request.TagValueQueryRequest;
import com.jingyuu.ershoujing.web.request.TagValueRequest;
import com.jingyuu.ershoujing.web.response.BaseResp;
import com.jingyuu.ershoujing.web.response.TagKeyResult;
import com.jingyuu.ershoujing.web.response.TagValueBatchResult;
import com.jingyuu.ershoujing.web.response.TagValueResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author owen
 * @date 2017-10-16
 */
@Api(tags = "商品标签健/值")
@RestController
@RequestMapping(value = "/tag")
public class TagController extends BaseController {
    @Autowired
    private TagService tagService;

    @Log("新增标签健")
    @ApiOperation(value = "新增标签健")
    @RequestMapping(value = "/key/save", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<TagKeyResult>> saveKey(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @RequestBody TagKeyRequest tagKeyRequest)
            throws JyuException {
        TagKeyEntity keyEntity = tagService.saveTagKey(
                TagKeyBo.builder()
                        .keyTypeEnum(TagKeyTypeEnum.fromValue(tagKeyRequest.getType()))
                        .key(tagKeyRequest.getKey())
                        .tipText(tagKeyRequest.getTipText())
                        .tipFid(tagKeyRequest.getTipFid())
                        .build());
        return ResponseEntity.ok(BaseResp.ok(keyEntity.toBean(TagKeyResult.class)));
    }

    @Log("查询标签健详情")
    @ApiOperation(value = "查询标签健详情")
    @RequestMapping(value = "/key/detail/{keyId}", method = RequestMethod.GET)
    public ResponseEntity<BaseResp<TagKeyResult>> getKeyDetail(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @PathVariable Long keyId)
            throws JyuException {
        TagKeyEntity keyEntity = tagService.loadTagKey(keyId);
        return ResponseEntity.ok(BaseResp.ok(keyEntity.toBean(TagKeyResult.class)));
    }

    @Log("查询标签健列表")
    @ApiOperation(value = "查询标签健列表")
    @RequestMapping(value = "/key/list", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<PageBean<TagKeyResult>>> listKey(@RequestHeader(JyuConstant.TOKEN_HEADER) String token,
                                                                    @RequestBody TagKeyQueryRequest tagKeyQueryRequest)
            throws JyuException {
        PageBean<TagKeyVo> tagKeyVoPageBean = tagService.listTagKey(
                PageQuery.<TagKeyQueryBo>builder()
                        .startIndex(tagKeyQueryRequest.getStartIndex())
                        .pageSize(tagKeyQueryRequest.getPageSize())
                        .conditions(
                                TagKeyQueryBo.builder()
                                        .type(tagKeyQueryRequest.getType())
                                        .key(tagKeyQueryRequest.getKey())
                                        .build())
                        .build()
        );
        return ResponseEntity.ok(
                BaseResp.ok(
                        PageBean.<TagKeyResult>builder()
                                .totalCount(tagKeyVoPageBean.getTotalCount())
                                .items(tagKeyVoPageBean.getItems().stream()
                                        .map(tagKeyVo -> tagKeyVo.toBean(TagKeyResult.class))
                                        .collect(Collectors.toList()))
                                .build()
                ));
    }

    @Log("查询标签值详情")
    @ApiOperation(value = "查询标签值详情")
    @RequestMapping(value = "/value/detail/{valueId}", method = RequestMethod.GET)
    public ResponseEntity<BaseResp<TagValueResult>> getValueDetail(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @PathVariable Long valueId) throws JyuException {
        TagValueEntity tagValueEntity = tagService.loadTagValue(valueId);
        TagValueResult tagValueResult = tagValueEntity.toBean(TagValueResult.class);
        tagValueResult.setValueType(TagValueTypeEnum.fromValue(tagValueEntity.getValueType()));
        return ResponseEntity.ok(BaseResp.ok(tagValueResult));
    }

    @Log("新增标签值")
    @ApiOperation(value = "新增标签值")
    @RequestMapping(value = "/value/save", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<TagValueResult>> saveValue(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @RequestBody TagValueRequest tagValueRequest)
            throws JyuException {
        TagValueEntity tagValueEntity = tagService.saveTagValue(
                TagValueBo.builder()
                        .keyId(tagValueRequest.getKeyId())
                        .value(tagValueRequest.getValue())
                        .valueTypeEnum(TagValueTypeEnum.fromValue(tagValueRequest.getValueType()))
                        .tipText(tagValueRequest.getTipText())
                        .tipFid(tagValueRequest.getTipFid())
                        .build());
        return ResponseEntity.ok(BaseResp.ok(tagValueEntity.toBean(TagValueResult.class)));
    }

    @Log("批量新增标签值")
    @ApiOperation(value = "批量新增标签值")
    @RequestMapping(value = "/value/save-batch", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<TagValueBatchResult>> saveValueBatch(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token, @RequestBody List<TagValueRequest> tagValueRequestList)
            throws JyuException {
        // 批量新增标签值
        List<String> result = tagService.saveTagValueBatch(
                tagValueRequestList.stream()
                        .map(tagValueRequest -> TagValueBo.builder()
                                .keyId(tagValueRequest.getKeyId())
                                .value(tagValueRequest.getValue())
                                .valueTypeEnum(TagValueTypeEnum.fromValue(tagValueRequest.getValueType()))
                                .tipText(tagValueRequest.getTipText())
                                .tipFid(tagValueRequest.getTipFid())
                                .build())
                        .collect(Collectors.toList()));
        return ResponseEntity.ok(BaseResp.ok(TagValueBatchResult.builder().tagValueSaveErrorList(result).build()));
    }

    @Log("查询标签值列表")
    @ApiOperation(value = "查询标签值列表")
    @RequestMapping(value = "/value/list", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<PageBean<TagValueResult>>> listValue(@RequestHeader(JyuConstant.TOKEN_HEADER) String token,
                                                                        @RequestBody TagValueQueryRequest tagValueQueryRequest)
            throws JyuException {
        PageBean<TagValueVo> tagValueVoPageBean = tagService.listTagValue(
                PageQuery.<TagValueQueryBo>builder()
                        .startIndex(tagValueQueryRequest.getStartIndex())
                        .pageSize(tagValueQueryRequest.getPageSize())
                        .conditions(tagValueQueryRequest.toBean(TagValueQueryBo.class))
                        .build()
        );
        return ResponseEntity.ok(
                BaseResp.ok(
                        PageBean.<TagValueResult>builder()
                                .totalCount(tagValueVoPageBean.getTotalCount())
                                .items(tagValueVoPageBean.getItems().stream()
                                        .map(tagValueVo -> {
                                            TagValueResult valueResult = tagValueVo.toBean(TagValueResult.class);
                                            valueResult.setValueType(TagValueTypeEnum.fromValue(tagValueVo.getValueType()));
                                            return valueResult;
                                        })
                                        .collect(Collectors.toList()))
                                .build()
                ));
    }
}
