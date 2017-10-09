package com.jingyuu.ershoujing.service.system.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileStorage {
    /**
     * 文件存储地址
     */
    private String url;
}
