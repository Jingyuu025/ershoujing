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
@ApiModel("标签值请求")
public class TagValueRequest {
    @ApiModelProperty(value = "标签键编号", required = true, example = "10001")
    @NotNull(message = "标签键编号缺失")
    private Long keyId;

    @ApiModelProperty(value = "标签值", example = "A1864")
    @NotNull(message = "标签值缺失")
    private String value;

    @ApiModelProperty(value = "标签值类型", required = true, allowableValues = "1:标签值短值,2:标签值长值", example = "1")
    @NotNull(message = "标签值类型缺失")
    private Integer valueType;

    @ApiModelProperty(value = "标签值提示")
    private String tipText;

    @ApiModelProperty(value = "标签值提示文件编号")
    private String tipFid;
}