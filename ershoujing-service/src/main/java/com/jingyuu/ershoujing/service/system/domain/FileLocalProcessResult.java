package com.jingyuu.ershoujing.service.system.domain;

import com.jingyuu.ershoujing.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileLocalProcessResult extends BaseBean {
    /**
     * 是否需要上传到远程服务器
     */
    private boolean uploadRemote;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件MD5
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
     * 文件本地存储
     */
    private String localPath;

    /**
     * 远程存储地址
     */
    private String remotePath;
}
