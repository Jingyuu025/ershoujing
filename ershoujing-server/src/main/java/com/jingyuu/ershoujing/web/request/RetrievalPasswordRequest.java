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
@ApiModel("找回密码请求")
public class RetrievalPasswordRequest {
    @ApiModelProperty(value = "手机号码", required = true, example = "13913317376")
    @NotNull(message = "手机号码")
    private String telephone;

    @ApiModelProperty(value = "验证码", required = true, example = "1234")
    @NotNull(message = "验证码")
    private String code;

    @ApiModelProperty(value = "密码", required = true, example = "654321")
    @NotNull(message = "密码缺失")
    private String password;

}
