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
@ApiModel("登陆请求")
public class LoginRequest {
    @ApiModelProperty(value = "终端 1:PC 2:Mobile", required = true, allowableValues = "1:PC,2:Mobile", example = "1")
    @Min(value = 1, message = "终端类型错误")
    @Max(value = 2, message = "终端类型错误")
    private Integer terminal;

    @ApiModelProperty(value = "手机", required = true, example = "13913317376")
    @NotNull(message = "手机缺失")
    private String telephone;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "短信验证码", example = "1024")
    private String code;
}
