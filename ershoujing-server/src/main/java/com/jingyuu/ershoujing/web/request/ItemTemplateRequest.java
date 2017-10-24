package com.jingyuu.ershoujing.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author owen
 * @date 2017-09-07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("商品模板请求")
public class ItemTemplateRequest {
    @ApiModelProperty(value = "商品类目编号", required = true, example = "1")
    @NotNull(message = "商品类目编号缺失")
    private Long categoryId;

    @ApiModelProperty(value = "商品品牌编号", required = true, example = "1")
    @NotNull(message = "商品品牌编号缺失")
    private Long brandId;

    @ApiModelProperty(value = "商品模板名称", required = true, example = "苹果 iPhone8")
    @NotNull(message = "商品模板名称缺失")
    private String itemName;

    @ApiModelProperty(value = "商品模板图片")
    private String itemFid;
}