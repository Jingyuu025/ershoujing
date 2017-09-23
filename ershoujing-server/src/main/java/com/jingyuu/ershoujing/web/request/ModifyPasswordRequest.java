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
@ApiModel("更新密码请求")
public class ModifyPasswordRequest {
    @ApiModelProperty(value = "旧密码", required = true, example = "123456")
    @NotNull(message = "旧密码缺失")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true, example = "654321")
    @NotNull(message = "新密码缺失")
    private String newPassword;

}
