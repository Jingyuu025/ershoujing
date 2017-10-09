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
@ApiModel("文件上传请求")
public class FileUploadRequest {
    @ApiModelProperty(value = "文件名称", required = true, example = "123456.jpg")
    @NotNull(message = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件流", required = true)
    private byte[] data;
}
