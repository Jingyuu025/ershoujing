package com.jingyuu.ershoujing.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author owen
 * @date 2017-09-07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("标签健请求")
public class TagKeyRequest {
    @ApiModelProperty(value = "标签健类型 1:基本信息 2:外观成色 3:功能性问题", required = true, example = "1")
    @Min(value = 1, message = "标签健类型错误")
    @Max(value = 3, message = "标签健类型错误")
    private Integer type;

    @ApiModelProperty(value = "标签健名称", required = true, example = "型号")
    @NotNull(message = "标签健名称缺失")
    private String key;

    @ApiModelProperty(value = "标签健提示", example = "如何查看小型号")
    private String tipText;

    @ApiModelProperty(value = "标签健提示文件编号", example = "b7f9a73bb986979f371dab46b6582b51")
    private String tipFid;
}