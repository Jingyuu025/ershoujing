package com.jingyuu.ershoujing.service.support.event;

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
@NoArgsConstructor
@AllArgsConstructor
public class FileSavedEvent implements JyuEvent {
    /**
     * 文件编号
     */
    private String fileId;

    /**
     * Bucket
     */
    private String bucketName;

    /**
     * 是否需要上传至远程
     */
    private boolean uploadToRemote;
}
