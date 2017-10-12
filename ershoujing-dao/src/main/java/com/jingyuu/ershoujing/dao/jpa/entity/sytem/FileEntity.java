package com.jingyuu.ershoujing.dao.jpa.entity.sytem;

import com.jingyuu.ershoujing.dao.base.BaseCustomIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author owen
 * @date 2017-09-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_file")
public class FileEntity extends BaseCustomIdEntity {
    @Id
    private String id;

    @Column(name = "file_type", columnDefinition = "VARCHAR(8) COMMENT '文件类型'")
    private String fileType;

    @Column(name = "file_name", columnDefinition = "VARCHAR(64) COMMENT '文件名称'")
    private String fileName;

    @Column(name = "file_md5", columnDefinition = "VARCHAR(64) COMMENT '文件MD5摘要'")
    private String fileMd5;

    @Column(name = "file_size", columnDefinition = "INTEGER COMMENT '文件大小'")
    private Long fileSize;

    @Column(name = "local_path", columnDefinition = "VARCHAR(256) COMMENT '图片本地存储'")
    private String localPath;

    @Column(name = "bucket_name", columnDefinition = "VARCHAR(128) COMMENT 'bucket'")
    private String bucketName;

    @Column(name = "remote_path", columnDefinition = "VARCHAR(256) COMMENT '图片远程存储'")
    private String remotePath;

    @Column(name = "state", columnDefinition = "SMALLINT(6) COMMENT '状态 1：文件存储本地 2：文件储存在远程 9：删除'")
    private Integer state;
}
