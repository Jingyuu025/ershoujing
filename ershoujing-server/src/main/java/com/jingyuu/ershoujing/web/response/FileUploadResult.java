package com.jingyuu.ershoujing.web.response;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResult extends BaseBean {
    /**
     * 文件编号
     */
    private String fileId;
}
