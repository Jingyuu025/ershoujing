package com.jingyuu.ershoujing.web.controller;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.common.utils.FileTypeEnum;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileGroupMappingEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.FileBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.FileVo;
import com.jingyuu.ershoujing.service.support.annotation.Log;
import com.jingyuu.ershoujing.service.system.FileService;
import com.jingyuu.ershoujing.web.domain.File;
import com.jingyuu.ershoujing.web.domain.FileGroup;
import com.jingyuu.ershoujing.web.domain.FileGroupMapping;
import com.jingyuu.ershoujing.web.response.BaseResp;
import com.jingyuu.ershoujing.web.response.FileUploadResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author owen
 * @date 2017-09-29
 */
@Api(tags = "文件")
@RequestMapping(value = "/file")
@RestController
public class FileController extends BaseController {
    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     */
    @Log("上传文件")
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<FileUploadResult>> upload(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token,
            @RequestBody MultipartFile file) throws JyuException, IOException {
        String fileName = file.getOriginalFilename();
        byte[] data = file.getBytes();

        // 上传文件
        FileVo fileVo = fileService.saveFile(
                FileBo.builder()
                        .fileName(fileName)
                        .data(data)
                        .build()
        );
        return ResponseEntity.ok(
                BaseResp.ok(FileUploadResult.builder()
                        .fileId(fileVo.getFileId())
                        .build())
        );
    }


    /**
     * 查看文件
     *
     * @param fileId
     * @return
     * @throws JyuException
     */
    @ApiOperation(value = "查看图片文件，对外展示阿里云OSS url")
    @RequestMapping(value = "/pic/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<BaseResp<File>> file(@PathVariable String fileId) throws JyuException {
        // 查询文件详情
        FileEntity file = fileService.load(fileId);
        String fileType = file.getFileType().toLowerCase(); // 文件类型

        // 限定图片类型的文件,支持bmp,jpg,gif,png
        if (FileTypeEnum.BMP.getExtension().equals(fileType) ||
                FileTypeEnum.JPEG.getExtension().equals(fileType) ||
                FileTypeEnum.GIF.getExtension().equals(fileType) ||
                FileTypeEnum.PNG.getExtension().equals(fileType)) {

            return ResponseEntity.ok(
                    BaseResp.ok(File.builder()
                            .fileId(fileId)
                            .remotePath(file.getRemotePath())
                            .build()
                    ));
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * 查看图片文件
     *
     * @param picId
     * @return
     * @throws JyuException
     */
    @ApiOperation(value = "查看图片文件(对外统一一个url)")
    @RequestMapping(value = "/pic/v2/{picId}", method = RequestMethod.GET)
    public ResponseEntity pic(@PathVariable String picId) throws JyuException {
        // 查询文件详情
        FileEntity file = fileService.load(picId);
        String fileType = file.getFileType().toLowerCase(); // 文件类型

        // 限定图片类型的文件,支持bmp,jpg,gif,png
        if (FileTypeEnum.BMP.getExtension().equals(fileType) ||
                FileTypeEnum.JPEG.getExtension().equals(fileType) ||
                FileTypeEnum.GIF.getExtension().equals(fileType) ||
                FileTypeEnum.PNG.getExtension().equals(fileType)) {

            byte[] bytes = fileService.readFile(picId);
            if (CommonUtil.isNotEmpty(bytes)) {
                // 查询图片文件详情
                HttpHeaders headers = new HttpHeaders();
                headers.add(CONTENT_TYPE, MimeMappings.DEFAULT.get(fileType));
                headers.setLastModified(file.getAddTime().getTime());

                byte[] result = bytes;
//            if (CommonUtils.isNotEmpty(picReq) && CommonUtils.allIsNotEmpty(picReq.getH(), picReq.getW())) {
//                result = ImageTool.thumbnail(bytes, 80, "jpg", picReq.getW(), picReq.getH());
//            } else {
//                result = bytes;
//            }
                return new ResponseEntity<>(result,
                        headers,
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * 申请文件组
     */
    @Log("申请文件组")
    @ApiOperation(value = "申请文件组")
    @RequestMapping(value = "/group/apply", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<FileGroup>> applyFileGroup(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token) throws JyuException, IOException {
        String fileGroupId = fileService.applyFileGroup();
        return ResponseEntity.ok(BaseResp.ok(FileGroup.builder().groupId(fileGroupId).build()));
    }


    /**
     * 查看文件图片组
     *
     * @param groupId 图片组编号
     * @return
     * @throws JyuException
     */
    @ApiOperation(value = "查看文件图片组")
    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<BaseResp<List<FileGroupMapping>>> group(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token,
            @PathVariable String groupId) throws JyuException {
        // 查询文件组和文件映射列表
        List<FileGroupMappingEntity> fileGroupMappingList = fileService.listFileGroupMapping(groupId);
        return new ResponseEntity<>(
                BaseResp.ok(
                        Optional.ofNullable(fileGroupMappingList)
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(fileGroupMappingEntity -> fileGroupMappingEntity.toBean(FileGroupMapping.class))
                                .collect(Collectors.toList()))
                , HttpStatus.OK);
    }
}
