package com.jingyuu.ershoujing.service.system.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author owen
 * @date 2017-09-27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfig {
    /**
     * 单个文件大小限制
     */
    private long maxFileSize;

    /**
     * 存储
     */
    private Storage storage;

    /**
     * 定时任务
     */
    private Cron cron;

    /**
     * OSS
     */
    private OSS oss;

    @Data
    public static class Storage {
        /**
         * 本地存储
         */
        private String local;

        /**
         * 远程存储
         */
        private String remote;
    }


    @Data
    public static class Cron {
        /**
         * 上传远程定时任务配置
         */
        private String uploadToRemote;

        /**
         * 清除本地存储定时任务配置
         */
        private String clearLocalStorage;
    }

    @Data
    public static class OSS {
        /**
         * EndPoint
         */
        private String endpoint;
        /**
         * AccessKeyId
         */
        private String accessKeyId;
        /**
         * AccessKeySecret
         */
        private String accessKeySecret;
        /**
         * Bucket
         */
        private Bucket bucket;

        @Data
        public static class Bucket {
            /**
             * 默认bucket
             */
            private String defaultBucket;
            /**
             * JYU bucket
             */
            private String jyuBucket;
        }
    }
}
