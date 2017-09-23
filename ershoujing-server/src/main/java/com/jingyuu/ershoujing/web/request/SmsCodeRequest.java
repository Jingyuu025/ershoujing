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
@ApiModel("获取短信验证码请求")
public class SmsCodeRequest {
    @ApiModelProperty(value = "手机", required = true, example = "13913317376")
    @NotNull(message = "手机缺失")
    private String telephone;

    @ApiModelProperty(value = "业务类型 1：注册,2：登录", required = true, allowableValues = "1：注册,2：登录", example = "1")
    private Integer businessType;
}
