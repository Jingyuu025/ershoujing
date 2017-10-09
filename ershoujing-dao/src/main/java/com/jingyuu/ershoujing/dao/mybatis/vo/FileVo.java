package com.jingyuu.ershoujing.dao.mybatis.vo;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author owen
 * @date 2017-09-27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileVo extends BaseBean {
    /**
     * 文件唯一识
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件MD5值
     */
    private String fileMd5;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 创建时间
     */
    private Date createdTime;
}
