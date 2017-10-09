package com.jingyuu.ershoujing.service.system;

import com.jingyuu.ershoujing.dao.mybatis.bo.FileBo;
import com.jingyuu.ershoujing.service.system.domain.FileLocalProcessResult;

import java.io.IOException;

/**
 * @author owen
 * @date 2017-09-28
 */
public interface FileProcessor {
    /**
     * 处理文件
     *
     * @param fileBo
     * @return
     */
    FileLocalProcessResult process(FileBo fileBo) throws IOException;
}
