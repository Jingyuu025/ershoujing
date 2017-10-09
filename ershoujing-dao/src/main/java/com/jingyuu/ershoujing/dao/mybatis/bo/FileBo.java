package com.jingyuu.ershoujing.dao.mybatis.bo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileBo extends BaseBean {
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * bucketName
     */
    private String bucketName;

    /**
     * 文件内容
     */
    private byte[] data;
}
