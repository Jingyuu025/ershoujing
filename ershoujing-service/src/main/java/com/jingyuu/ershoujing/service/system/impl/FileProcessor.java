package com.jingyuu.ershoujing.service.system.impl;

import com.jingyuu.ershoujing.common.statics.enums.FileStateEnum;
import com.jingyuu.ershoujing.common.utils.*;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.FileBo;
import com.jingyuu.ershoujing.service.system.FileLocalProcessor;
import com.jingyuu.ershoujing.service.system.FileReadProcessor;
import com.jingyuu.ershoujing.service.system.FileService;
import com.jingyuu.ershoujing.service.system.domain.FileLocalProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import static com.jingyuu.ershoujing.common.utils.DateUtil.DateToString;

/**
 * @author owen
 * @date 2017-09-28
 */
@Slf4j
@Component
public class FileLocalProcessorImpl implements FileLocalProcessor, FileReadProcessor {
    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private FileService fileService;

    /**
     * 处理文件
     *
     * @param fileBo
     * @return
     */
    public FileLocalProcessResult process(FileBo fileBo) throws IOException {
        String fileName = fileBo.getFileName();
        byte[] data = fileBo.getData();

        // 文件查重
        String fileMd5 = DigestUtils.md5DigestAsHex(data);
        FileEntity fileEntity = fileService.getByFileMd5(fileMd5);
        if (CommonUtil.isNotEmpty(fileEntity)) {
            int state = fileEntity.getState();
            FileStateEnum fileStateEnum = FileStateEnum.fromValue(state);
            return FileLocalProcessResult.builder()
                    .fileMd5(fileMd5)
                    .fileName(fileName)
                    .fileSize(fileEntity.getFileSize())
                    .fileType(fileEntity.getFileType())
                    .localPath(fileEntity.getLocalPath())
                    .remotePath(fileEntity.getRemotePath())
                    .uploadRemote(needUploadToRemote(fileStateEnum)) // 是否需要上传至远程
                    .build();
        } else {
            // 目的地址
            String dicPath = this.generateDic(fileBo);
            File dic = this.initPath(fileConfig.getStorage().getLocal(), dicPath);
            long fileSize = data.length; // 文件大小

            // 识别文件类型
            String fileType = null;
            FileType fileTypeEnum = FileTypeUtil.getFileTypeByStream(data);
            // 目前通过流取文件类型可信任 JPG GIF BMP 三种, 其他的通过扩展名读取
            if (CommonUtil.isNotEmpty(fileTypeEnum) && (
                    FileType.JPEG.equals(fileTypeEnum) ||
                            FileType.GIF.equals(fileTypeEnum) ||
                            FileType.BMP.equals(fileTypeEnum))) {
                fileType = fileTypeEnum.getExtension();
            } else {
                int index = fileBo.getFileName().lastIndexOf('.');
                if (index > 0) {
                    fileType = fileBo.getFileName().substring(index + 1);
                }
            }

            // 随机处理文件名称
            fileName = this.generateFileName(fileType);
            String filePath = dic.getPath() + File.separator + fileName;
            File file = new File(filePath);

            // 把文件写入local
            FileUtils.writeByteArrayToFile(file, data);
            return FileLocalProcessResult.builder()
                    .fileMd5(fileMd5)
                    .fileName(fileName)
                    .fileType(fileType)
                    .fileSize(fileSize)
                    .localPath(filePath)
                    .uploadRemote(true)
                    .build();
        }
    }


    @Override
    public byte[] readFile(FileEntity fileEntity) {
        try {
            String localPath = fileEntity.getLocalPath(); //本地文件存储
            return FileUtils.readFileToByteArray(new File(localPath));
        } catch (IOException e) {
            log.error("读取本地文件出错, fileEntity:{}", fileEntity);
        }
        return null;

    }


    /***
     * 是否需要上传至远程服务器
     *
     * 策略：
     * 文件删除操作做软删除，远程服务器上图片不删除
     *
     * @param fileStateEnum
     * @return
     */
    private boolean needUploadToRemote(FileStateEnum fileStateEnum) {
        if (FileStateEnum.STORAGE_REMOTE.equals(fileStateEnum) || FileStateEnum.DELETE.equals(fileStateEnum)) {
            return false;
        }
        return true;
    }

    /**
     * 初始目录，文件按日期存储
     * 规则 localPath/YYYY-MM-dd/
     *
     * @param localPath 本地存储目录
     * @param dic       文件存储目录
     * @return
     */
    private File initPath(String localPath, String dic) {
        String path = localPath + File.separator + dic;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 文件名字随机处理
     *
     * @param fileType 文件类型
     * @return
     */
    private String generateFileName(String fileType) {
        String fileName = new Date().getTime() + RandomUtil.createRandomNumber(6);
        if (null != fileType) {
            return fileName + "." + fileType;
        } else {
            return fileName + ".unknown";
        }
    }

    /**
     * 生成目标地址
     *
     * @param fileBo
     * @return
     */
    private String generateDic(FileBo fileBo) {
        return DateToString(Date.from(Instant.now()), DateUtil.DEFAULT_DATE_FORMAT);
    }

}
