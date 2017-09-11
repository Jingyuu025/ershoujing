package com.jingyuu.ershoujing.web.request;

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
public class LoginResult {
    @NotNull(message = "手机缺失")
    private String telephone;

    @NotNull(message = "密码缺失")
    private String password;
}
