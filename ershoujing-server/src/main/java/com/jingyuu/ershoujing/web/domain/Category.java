package com.jingyuu.ershoujing.web.domain;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-10-06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseBean {
    /**
     * 文件编号
     */
    private String fileId;

    /**
     * 远程地址
     */
    private String remotePath;
}
