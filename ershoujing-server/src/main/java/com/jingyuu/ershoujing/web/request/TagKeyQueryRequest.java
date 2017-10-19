package com.jingyuu.ershoujing.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author owen
 * @date 2017-09-07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("标签健查询请求")
public class TagKeyQueryRequest extends BasePageRequest {
    @ApiModelProperty(value = "标签健类型 1:基本信息 2:外观成色 3:功能性问题", example = "1")
    @Min(value = 1, message = "标签健类型错误")
    @Max(value = 3, message = "标签健类型错误")
    private Integer type;

    @ApiModelProperty(value = "标签健名称", example = "型号")
    private String key;
}