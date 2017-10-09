package com.jingyuu.ershoujing.service.support.event.listener;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.FileStateEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.system.FileRepository;
import com.jingyuu.ershoujing.service.support.event.FileSavedEvent;
import com.jingyuu.ershoujing.service.system.FileService;
import com.jingyuu.ershoujing.service.system.FileUploadProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author owen
 * @date 2017-10-06
 */
@Slf4j
@Component
public class FileSavedListener {
    @Autowired
    private FileUploadProcessor fileUploadProcessor;
    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;

    /**
     * 监听文件保存事件
     *
     * @param fileSavedEvent
     */
    @EventListener(condition = "#fileSavedEvent.uploadToRemote == true")
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onFileSaved(FileSavedEvent fileSavedEvent) {
        String fileId = fileSavedEvent.getFileId();
        String bucketName = fileSavedEvent.getBucketName();
        String remotePaht = null;
        try {
            if (CommonUtil.isEmpty(bucketName)) {
                remotePaht = fileUploadProcessor.upload(fileId);
            } else {
                remotePaht = fileUploadProcessor.upload(bucketName, fileId);
            }

            // 更新文件信息
            if (CommonUtil.isNotEmpty(remotePaht)) {
                FileEntity fileEntity = fileService.load(fileId);
                fileEntity.setRemotePath(remotePaht);
                fileEntity.setState(FileStateEnum.STORAGE_REMOTE.getValue());

                fileRepository.save(fileEntity);
            }
        } catch (JyuException e) {
            log.error("监听文件保存事件异常，异常信息:{}", e);
        }
    }
}
