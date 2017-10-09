package com.jingyuu.ershoujing.service.system;

import com.jingyuu.ershoujing.common.exception.JyuException;

/**
 * @author owen
 * @date 2017-10-02
 */
public interface FileUploadProcessor {
    /**
     * 文件上传
     *
     * @param fileId 文件编号
     * @return
     * @throws JyuException
     */
    String upload(String fileId) throws JyuException;

    /**
     * 文件上传
     * 上传文件至指定的Bucket
     *
     * @param bucketName bucket
     * @param fileId     文件编号
     * @return
     * @throws JyuException
     */
    String upload(String bucketName, String fileId) throws JyuException;
}
