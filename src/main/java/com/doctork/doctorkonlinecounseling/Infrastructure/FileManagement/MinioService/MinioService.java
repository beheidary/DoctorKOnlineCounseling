package com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.MinioService;

import com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.Utils.FileResponse;
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface MinioService {

    // Upload files in the bucket
    FileResponse putObject(MultipartFile multipartFile, String fileType);

    // Create a bucket
    Boolean makeBucket(String bucketName) throws Exception;

    //Check Whether bucket already exists
    boolean bucketExists(String bucketName) throws Exception;

    // List all bucket names
    List<String> listBucketName() throws Exception;

    //List all buckets
    List<Bucket> listBuckets() throws Exception;

    // Delete Bucket by Name
    Boolean removeBucket(String bucketName) throws Exception;

    // List all object names in the bucket
    List<String> listObjectNames(String bucketName) throws Exception;

    // Download file from bucket
    InputStream downloadObject(String objectName) throws Exception;

    // Delete file in bucket
    Boolean removeObject(String objectName) throws Exception;

    // Delete files in bucket
    Boolean removeListObject(String bucketName, List<String> objectNameList) throws Exception;

    // Get file path from bucket
    String getObjectUrl(String bucketName,String objectName) throws Exception;

    String uploadFile(byte[] bytes, String fileName);
}
