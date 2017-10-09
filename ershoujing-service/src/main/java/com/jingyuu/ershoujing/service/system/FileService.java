package com.jingyuu.ershoujing.service.system;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileGroupEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileGroupMappingEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.FileBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.FileVo;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-07
 */
public interface FileService {
    /**
     * 申请文件组
     *
     * @return
     */
    String applyFileGroup();

    /**
     * 查询文件组详情
     *
     * @param groupId 文件组编号
     * @return
     */
    FileGroupEntity getFileGroup(String groupId);

    /**
     * 查询文件组详情
     *
     * @param groupId 文件组编号
     * @return
     * @throws JyuException
     */
    FileGroupEntity loadFileGroup(String groupId) throws JyuException;

//    /**
//     * 删除文件组
//     *
//     * @param fileGroupId 文件组编号
//     * @throws JyuException
//     */
//    void removeGroup(String fileGroupId) throws JyuException;
//
//    /**
//     * 拷贝文件组
//     * 拷贝文件组，同时拷贝该文件组下面的文件
//     *
//     * @param fileGroupId 文件组编号
//     * @return
//     * @throws JyuException
//     */
//    String copyFileGroup(String fileGroupId) throws JyuException;

    /**
     * 上传文件
     *
     * @param fileBo 文件信息
     * @return
     * @throws JyuException
     */
    FileVo saveFile(FileBo fileBo) throws JyuException;

    /**
     * 上传文件到指定的文件组
     *
     * @param fileGroupId 文件组信息
     * @param fileBo      文件信息
     * @return
     * @throws JyuException
     */
    FileVo saveFileToGroup(String fileGroupId, FileBo fileBo) throws JyuException;

    /**
     * 查询文件信息
     *
     * @param fileId 文件编号
     * @return
     */
    FileEntity get(String fileId);

    /**
     * 查询文件信息
     *
     * @param fileId 文件编号
     * @return
     * @throws JyuException
     */
    FileEntity load(String fileId) throws JyuException;

    /**
     * 查询文件信息
     *
     * @param fileMd5 文件MD5摘要
     * @return
     * @throws JyuException
     */
    FileEntity getByFileMd5(String fileMd5);

    /**
     * 查询文件信息
     *
     * @param fileMd5 文件MD5摘要
     * @return
     * @throws JyuException
     */
    FileEntity loadByFileMd5(String fileMd5) throws JyuException;

    /**
     * 读取文件信息
     *
     * @param fileId 文件编号
     * @return
     */
    byte[] readFile(String fileId) throws JyuException;

//
//    /**
//     * 删除文件
//     *
//     * @param fileId 文件编号
//     * @throws JyuException
//     */
//    void removeFile(String fileId) throws JyuException;
//
//    /**
//     * 从指定的文件组删除文件
//     *
//     * @param fileId      文件编号
//     * @param fileGroupId 文件组编号
//     * @throws JyuException
//     */
//    void removeFileFromGroup(String fileId, String fileGroupId) throws JyuException;

    /**
     * 查询文件组信息
     *
     * @param groupId 文件组编号
     * @return
     */
    List<FileGroupMappingEntity> listFileGroupMapping(String groupId);
}
