package com.jingyuu.ershoujing.service.system.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.FileStateEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.system.FileRepository;
import com.jingyuu.ershoujing.service.support.AliyunSupport;
import com.jingyuu.ershoujing.service.system.FileReadProcessor;
import com.jingyuu.ershoujing.service.system.FileService;
import com.jingyuu.ershoujing.service.system.FileUploadProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author owen
 * @date 2017-10-02
 */
@Service(value = "aliyunOssProcessor")
public class AliyunOssProcessorImpl implements FileUploadProcessor, FileReadProcessor {
    @Autowired
    private AliyunSupport aliyunSupport;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public String upload(String fileId) throws JyuException {
        // 查询文件详情
        FileEntity fileEntity = fileService.load(fileId);

        // 上传文件
        String remotePath = aliyunSupport.upload(fileEntity);
        if (CommonUtil.isNotEmpty(remotePath)) {
            fileEntity.setRemotePath(remotePath);
            fileEntity.setState(FileStateEnum.STORAGE_REMOTE.getValue()); // 远程存储

            fileRepository.save(fileEntity);
        }
        return remotePath;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public String upload(String bucketName, String fileId) throws JyuException {
        // 查询文件详情
        FileEntity fileEntity = fileService.load(fileId);

        // 上传文件
        String remotePath = aliyunSupport.upload(bucketName, fileEntity);
        if (CommonUtil.isNotEmpty(remotePath)) {
            fileEntity.setRemotePath(remotePath);
            fileEntity.setState(FileStateEnum.STORAGE_REMOTE.getValue()); // 远程存储

            fileRepository.save(fileEntity);
        }
        return remotePath;
    }

    @Override
    public byte[] readFile(FileEntity fileEntity) {
        return aliyunSupport.read(fileEntity);
    }
}
