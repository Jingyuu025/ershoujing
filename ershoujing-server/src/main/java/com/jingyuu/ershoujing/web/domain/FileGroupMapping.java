package com.jingyuu.ershoujing.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-10-09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileGroupMapping {
    /**
     * 文件组编号
     */
    private String groupId;

    /**
     * 文件编号
     */
    private String fileId;
}
