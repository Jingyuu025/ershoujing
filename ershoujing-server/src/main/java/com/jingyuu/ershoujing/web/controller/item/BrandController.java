package com.jingyuu.ershoujing.web.controller.item;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.dao.jpa.entity.item.CategoryEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.CategoryBo;
import com.jingyuu.ershoujing.service.item.CategoryService;
import com.jingyuu.ershoujing.service.support.annotation.Log;
import com.jingyuu.ershoujing.web.controller.BaseController;
import com.jingyuu.ershoujing.web.domain.Category;
import com.jingyuu.ershoujing.web.request.CategoryRequest;
import com.jingyuu.ershoujing.web.response.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author owen
 * @date 2017-10-09
 */
@Api(tags = "类目")
@RestController
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增类目
     */
    @Log("新增类目")
    @ApiOperation(value = "新增类目")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<BaseResp> save(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token,
            @RequestBody CategoryRequest categoryRequest) throws JyuException {
        categoryService.save(
                CategoryBo.builder()
                        .cName(categoryRequest.getCName())
                        .build()
        );
        return ResponseEntity.ok(BaseResp.ok());
    }

    /**
     * 查询类目列表
     */
    @Log("查询类目列表")
    @ApiOperation(value = "查询类目列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<BaseResp<List<Category>>> list(@RequestHeader(JyuConstant.TOKEN_HEADER) String token) {
        List<CategoryEntity> categoryList = categoryService.listCategory();
        return ResponseEntity.ok(
                BaseResp.ok(
                        Optional.ofNullable(categoryList).orElse(Collections.emptyList())
                                .stream()
                                .map(categoryEntity -> Category.builder()
                                        .cId(categoryEntity.getId())
                                        .cName(categoryEntity.getCName())
                                        .build())
                                .collect(Collectors.toList())
                ));
    }
}
