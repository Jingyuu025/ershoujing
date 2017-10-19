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
@ApiModel("品牌请求")
public class BrandRequest {
    @ApiModelProperty(value = "品牌名称", required = true, example = "华为")
    @NotNull(message = "品牌名称缺失")
    private String brandName;

    @ApiModelProperty(value = "品牌LOGO文件编号",  example = "b7f9a73bb986979f371dab46b6582b51")
    private String logoFid;
}