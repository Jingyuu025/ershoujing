package com.jingyuu.ershoujing.service.support;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.FileEntity;
import com.jingyuu.ershoujing.service.system.impl.FileConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author owen
 * @date 2017-09-25
 */
@Slf4j
@Component
public class AliyunSupport {
    @Autowired
    private FileConfig fileConfig;

    /**
     * 释放资源
     *
     * @param ossClient
     */
    private static void destroy(OSSClient ossClient) {
        ossClient.shutdown();
    }

    /**
     * 上传文件至指定的Bucket
     *
     * @param bucketName
     * @param fileEntity
     * @return
     */
    public String upload(String bucketName, FileEntity fileEntity) {
        // 创建OSS Client
        OSSClient ossClient = init();
        String fileId = fileEntity.getId();
        String localPath = fileEntity.getLocalPath();

        try {
            // 上传文件
            File localFile = FileUtils.getFile(localPath);
            PutObjectRequest putObjectReq = new PutObjectRequest(bucketName, fileId, localFile);
            ossClient.putObject(putObjectReq);
            return bucketName.concat(".")
                    .concat(fileConfig.getOss().getEndpoint().replaceAll("http://",""))
                    .concat("/").concat(fileId);
        } catch (Exception oe) {
            log.error("上传图片发生异常！异常信息:{}", oe);
        } finally {
            destroy(ossClient);
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param fileEntity 文件信息
     */
    public String upload(FileEntity fileEntity) {
        return upload(fileConfig.getOss().getBucket().getDefaultBucket(), fileEntity);
    }


    /**
     * 读取文件
     *
     * @param bucketName
     * @param fileEntity
     * @return
     */
    public byte[] read(String bucketName, FileEntity fileEntity) {
        OSSClient ossClient = init();
        byte[] data = null;

        String fileId = fileEntity.getId();
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, fileId);
            OSSObject ossObject = ossClient.getObject(getObjectRequest);
            if (CommonUtil.isNotEmpty(ossObject)) {
                data = IOUtils.toByteArray(ossObject.getObjectContent());
            }
        } catch (Exception e) {
            log.error("下载文件发生异常！异常信息:{}", e);
        }

        return data;
    }

    public byte[] read(FileEntity fileEntity) {
        return read(fileConfig.getOss().getBucket().getDefaultBucket(), fileEntity);
    }

    /**
     * 初始创建OSS Client
     *
     * @return
     */
    private OSSClient init() {
        return new OSSClient(
                fileConfig.getOss().getEndpoint(),
                fileConfig.getOss().getAccessKeyId(),
                fileConfig.getOss().getAccessKeySecret());
    }


//    public static void main(String[] args) throws IOException {
//        /*
//         * Constructs a client instance with your account for accessing OSS
//         */
//        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//
//        System.out.println("Getting Started with OSS SDK for Java\n");
//
//        try {
//
//            /*
//             * Determine whether the bucket exists
//             */
//            if (!ossClient.doesBucketExist(bucketName)) {
//                /*
//                 * Create a new OSS bucket
//                 */
//                System.out.println("Creating bucket " + bucketName + "\n");
//                ossClient.createBucket(bucketName);
//                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
//                ossClient.createBucket(createBucketRequest);
//            }
//
//            /*
//             * List the buckets in your account
//             */
//            System.out.println("Listing buckets");
//
//            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
//            listBucketsRequest.setMaxKeys(500);
//
//            for (Bucket bucket : ossClient.listBuckets()) {
//                System.out.println(" - " + bucket.getName());
//            }
//            System.out.println();
//
//            /*
//             * Upload an object to your bucket
//             */
//            System.out.println("Uploading a new object to OSS from a file\n");
//            ossClient.putObject(new PutObjectRequest(bucketName, key, createSampleFile()));
//
//            /*
//             * Determine whether an object residents in your bucket
//             */
//            boolean exists = ossClient.doesObjectExist(bucketName, key);
//            System.out.println("Does object " + bucketName + " exist? " + exists + "\n");
//
//            ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
//            ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.Default);
//
//            ObjectAcl objectAcl = ossClient.getObjectAcl(bucketName, key);
//            System.out.println("ACL:" + objectAcl.getPermission().toString());
//
//            /*
//             * Download an object from your bucket
//             */
//            System.out.println("Downloading an object");
//            OSSObject object = ossClient.getObject(bucketName, key);
//            System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
//            displayTextInputStream(object.getObjectContent());
//
//            /*
//             * List objects in your bucket by prefix
//             */
//            System.out.println("Listing objects");
//            ObjectListing objectListing = ossClient.listObjects(bucketName, "My");
//            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//                System.out.println(" - " + objectSummary.getKey() + "  " +
//                        "(size = " + objectSummary.getSize() + ")");
//            }
//            System.out.println();
//
//            /*
//             * Delete an object
//             */
//            System.out.println("Deleting an object\n");
//            ossClient.deleteObject(bucketName, key);
//
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message: " + oe.getErrorCode());
//            System.out.println("Error Code:       " + oe.getErrorCode());
//            System.out.println("Request ID:      " + oe.getRequestId());
//            System.out.println("Host ID:           " + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message: " + ce.getMessage());
//        } finally {
//            /*
//             * Do not forget to shut down the client finally to release all allocated resources.
//             */
//            ossClient.shutdown();
//        }
//    }
//
//    private static File createSampleFile() throws IOException {
//        File file = File.createTempFile("oss-java-sdk-", ".txt");
//        file.deleteOnExit();
//
//        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
//        writer.write("abcdefghijklmnopqrstuvwxyz\n");
//        writer.write("0123456789011234567890\n");
//        writer.close();
//
//        return file;
//    }
//
//    private static void displayTextInputStream(InputStream input) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//        while (true) {
//            String line = reader.readLine();
//            if (line == null) break;
//
//            System.out.println("    " + line);
//        }
//        System.out.println();
//
//        reader.close();
//    }
}
