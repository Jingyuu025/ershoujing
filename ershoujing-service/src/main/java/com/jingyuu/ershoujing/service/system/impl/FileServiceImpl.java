package com.jingyuu.ershoujing.service.system.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.statics.enums.FileStateEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.common.utils.Md5Util;
import com.jingyuu.ershoujing.common.utils.RandomUtil;
import com.jingyuu.ershoujing.common.utils.UUIDUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileGroupEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileGroupMappingEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.system.FileGroupMappingRepository;
import com.jingyuu.ershoujing.dao.jpa.repository.system.FileGroupRepository;
import com.jingyuu.ershoujing.dao.jpa.repository.system.FileRepository;
import com.jingyuu.ershoujing.dao.mybatis.bo.FileBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.FileVo;
import com.jingyuu.ershoujing.service.support.event.FileSavedEvent;
import com.jingyuu.ershoujing.service.system.FileProcessor;
import com.jingyuu.ershoujing.service.system.FileReadProcessor;
import com.jingyuu.ershoujing.service.system.FileService;
import com.jingyuu.ershoujing.service.system.domain.FileLocalProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author owen
 * @date 2017-09-08
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileProcessor fileLocalProcessor;
    @Autowired
    private FileReadProcessor fileLocalReadProcessor;
    @Autowired
    private FileReadProcessor aliyunOssProcessor;

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileGroupRepository fileGroupRepository;
    @Autowired
    private FileGroupMappingRepository fileGroupMappingRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public String applyFileGroup() {
        String fileGroupId = UUIDUtil.uuidIngoreHyphen();
        fileGroupRepository.save(FileGroupEntity.builder().id(fileGroupId).build());
        return fileGroupId;
    }

    @Override
    @Transactional(readOnly = true)
    public FileGroupEntity getFileGroup(String fileGroupId) {
        return fileGroupRepository.findOne(fileGroupId);
    }

    @Override
    @Transactional(readOnly = true)
    public FileGroupEntity loadFileGroup(String groupId) throws JyuException {
        FileGroupEntity fileGroupEntity = this.getFileGroup(groupId);
        if (CommonUtil.isEmpty(fileGroupEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "文件组不存在，文件组编号:" + groupId);
        }

        return fileGroupEntity;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public FileVo saveFile(FileBo fileBo) throws JyuException {
        try {
            // 文件本地解析
            FileLocalProcessResult fileLocalProcessResult = fileLocalProcessor.process(fileBo);

            // 保存文件
            FileEntity fileEntity = fileLocalProcessResult.toBean(FileEntity.class);
            String fileId = Md5Util.md5(
                    String.valueOf(System.currentTimeMillis())
                            .concat(RandomUtil.createRandomNumberAndChar(8))
                            .concat(fileEntity.getFileMd5()));
            fileEntity.setId(fileId); // 自定义文件编号

            // 是否需要上传至远程服务器
            boolean uploadToRemote = fileLocalProcessResult.isUploadRemote();
            if (uploadToRemote) {
                fileEntity.setState(FileStateEnum.STORAGE_LOCAL.getValue());
            } else {
                fileEntity.setState(FileStateEnum.STORAGE_REMOTE.getValue());
            }
            fileEntity = fileRepository.save(fileEntity);

            // 发布文件保存事件
            String bucketName = fileBo.getBucketName();
            eventPublisher.publishEvent(
                    FileSavedEvent.builder()
                            .fileId(fileId)
                            .bucketName(bucketName)
                            .uploadToRemote(uploadToRemote)
                            .build());

            return FileVo.builder()
                    .fileId(fileId)
                    .fileName(fileEntity.getFileName())
                    .fileMd5(fileEntity.getFileMd5())
                    .fileType(fileEntity.getFileType())
                    .fileSize(fileEntity.getFileSize())
                    .createdTime(fileEntity.getAddTime())
                    .build();
        } catch (IOException e) {
            log.error("文件上传异常！ 异常信息: {}", e);
        }
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public FileVo saveFileToGroup(String groupId, FileBo fileBo) throws JyuException {
        // 验证文件组是否存在
        FileGroupEntity fileGroupEntity = this.loadFileGroup(groupId);
        String fileGroupId = fileGroupEntity.getId();

        // 上传文件
        FileVo fileVo = this.saveFile(fileBo);
        String fileId = fileVo.getFileId();
        if (CommonUtil.isNotEmpty(fileVo)) {
            // 建立文件与文件组映射关系
            fileGroupMappingRepository.save(
                    FileGroupMappingEntity.builder()
                            .groupId(fileGroupId)
                            .fileId(fileId)
                            .build()
            );

            fileVo.setGroupId(groupId);
            return fileVo;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public FileEntity get(String fileId) {
        return fileRepository.findOne(fileId);
    }

    @Override
    @Transactional(readOnly = true)
    public FileEntity load(String fileId) throws JyuException {
        FileEntity fileEntity = this.get(fileId);
        if (CommonUtil.isEmpty(fileEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "文件不存在！文件编号：" + fileId);
        }

        return fileEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public FileEntity getByFileMd5(String fileMd5) {
        List<FileEntity> fileList = fileRepository.findByFileMd5(fileMd5);
        if (CommonUtil.isEmpty(fileList)) {
            return null;
        }
        return fileList.get(0);
    }


    @Override
    @Transactional(readOnly = true)
    public FileEntity loadByFileMd5(String fileMd5) throws JyuException {
        FileEntity fileEntity = this.getByFileMd5(fileMd5);
        if (CommonUtil.isEmpty(fileEntity)) {
            throw new JyuException(ErrorEnum.DATA_NOT_FOUND, "文件不存在！文件MD5：" + fileMd5);
        }
        return fileEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] readFile(String fileId) throws JyuException {
        FileEntity fileEntity = this.load(fileId);
        int state = fileEntity.getState();
        FileStateEnum fileState = FileStateEnum.fromValue(state);

        byte[] data = null;
        if (FileStateEnum.STORAGE_LOCAL.equals(fileState)) {
            // 文件存储在本地
            data = fileLocalReadProcessor.readFile(fileEntity);
        } else if (FileStateEnum.STORAGE_REMOTE.equals(fileState)) {
            // 文件存储在远程
            data = aliyunOssProcessor.readFile(fileEntity);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileGroupMappingEntity> listFileGroupMapping(String groupId) {
        return fileGroupMappingRepository.findByGroupId(groupId);
    }
}
