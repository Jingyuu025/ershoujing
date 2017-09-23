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
@ApiModel("注册请求")
public class RegisterRequest {
    @ApiModelProperty(value = "手机", required = true, example = "13913317376")
    @NotNull(message = "手机缺失")
    private String telephone;

    @ApiModelProperty(value = "短信验证码", required = true, example = "1024")
    @NotNull(message = "短信验证码缺失")
    private String code;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;
}
