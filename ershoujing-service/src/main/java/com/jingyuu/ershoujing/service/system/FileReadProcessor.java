package com.jingyuu.ershoujing.service.system;

import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;

/**
 * @author owen
 * @date 2017-09-30
 */
public interface FileReadProcessor {
    /**
     * 读取文件
     *
     * @param fileEntity
     * @return
     */
    byte[] readFile(FileEntity fileEntity);
}
