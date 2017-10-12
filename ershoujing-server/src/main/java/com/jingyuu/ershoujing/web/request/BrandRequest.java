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
@ApiModel("类目请求")
public class CategoryRequest {
    @ApiModelProperty(value = "类目名称", required = true, example = "手机")
    @NotNull(message = "类目名称缺失")
    private String cName;
}