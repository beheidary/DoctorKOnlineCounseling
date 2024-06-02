package com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.Utils;

import com.doctork.doctorkonlinecounseling.configuration.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class MinioUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioUtil.class);

    private final MinioClient minioClient;
    private final MinioConfig minioConfig;

    public MinioUtil(MinioClient minioClient, MinioConfig minioConfig) {
        this.minioClient = minioClient;
        this.minioConfig = minioConfig;
    }

    public void putObject(String bucketName, MultipartFile multipartFile, String filename, String fileType) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(filename).stream(
                                inputStream, -1, minioConfig.getFileSize())
                        .contentType(fileType)
                        .build());
    }

    public boolean bucketExists(String bucketName) throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        LOGGER.info("MinioUtil | bucketExists | found : " + found);

        if (found) {
            LOGGER.info("MinioUtil | bucketExists | message : " + bucketName + " exists");
        } else {
            LOGGER.info("MinioUtil | bucketExists | message : " + bucketName + " does not exist");
        }
        return found;
    }

    public Boolean makeBucket(String bucketName) throws Exception {
        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | makeBucket | flag | bucketName: " + flag + "/" + bucketName);

        if (!flag) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            return true;
        } else {
            return false;
        }
    }

    public List<Bucket> listBuckets() throws Exception {
        return minioClient.listBuckets();
    }

    public List<String> listBucketNames() throws Exception {
        List<Bucket> bucketList = listBuckets();
        LOGGER.info("MinioUtil | listBucketNames | bucketList size : " + bucketList.size());

        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    public Iterable<Result<Item>> listObjects(String bucketName) throws Exception {
        LOGGER.info("MinioUtil | listObjects is called");

        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | listObjects | flag : " + flag);

        if (flag) {
            return minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return null;
    }

    public boolean removeBucket(String bucketName) throws Exception {
        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | removeBucket | flag : " + flag);

        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);

            for (Result<Item> result : myObjects) {
                Item item = result.get();
                LOGGER.info("MinioUtil | removeBucket | item size : " + item.size());

                if (item.size() > 0) {
                    return false;
                }
            }
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            flag = bucketExists(bucketName);
            LOGGER.info("MinioUtil | removeBucket | flag : " + flag);
            return !flag;
        }
        return false;
    }

    public List<String> listObjectNames(String bucketName) throws Exception {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | listObjectNames | flag : " + flag + "/" + bucketName);

        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        } else {
            listObjectNames.add("Bucket does not exist");
        }

        LOGGER.info("MinioUtil | listObjectNames | listObjectNames size : " + listObjectNames.size());
        return listObjectNames;
    }

    public Boolean removeObject(String bucketName, String objectName) throws Exception {
        boolean flag = bucketExists(bucketName);

        if (flag) {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return true;
        }
        return false;
    }

    public String getObjectUrl(String bucketName, String objectName) throws Exception {
        boolean flag = bucketExists(bucketName);
        String url = "";

        if (flag) {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(7, TimeUnit.DAYS)
                            .build());
            LOGGER.info("MinioUtil | getObjectUrl | url : " + url);
        }
        return url;
    }

    public StatObjectResponse statObject(String bucketName, String objectName) throws Exception {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            StatObjectResponse stat = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
            LOGGER.info("MinioUtil | statObject | stat : " + stat.toString());
            return stat;
        }
        return null;
    }

    public InputStream getObject(String bucketName, String objectName) throws Exception {
        boolean flag = bucketExists(bucketName);

        if (flag) {
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
                LOGGER.info("MinioUtil | getObject | stream : " + stream.toString());
                return stream;
            }
        }
        return null;
    }

    public InputStream getObject(String bucketName, String objectName, long offset, Long length) throws Exception {
        LOGGER.info("MinioUtil | getObject is called");
        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | getObject | flag : " + flag);

        if (flag) {
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).offset(offset).length(length).build());
                LOGGER.info("MinioUtil | getObject | stream : " + stream.toString());
                return stream;
            }
        }
        return null;
    }

    public Boolean removeObject(String bucketName, List<String> objectNames) throws Exception {
        boolean flag = bucketExists(bucketName);

        if (flag) {
            List<DeleteObject> objects = new LinkedList<>();
            for (String objectName : objectNames) {
                objects.add(new DeleteObject(objectName));
            }
            Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                LOGGER.info("MinioUtil | removeObject | error : " + error.objectName() + " " + error.message());
                return false;
            }
        }
        return true;
    }

    // Upload InputStream object to the specified bucket
    public boolean putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception {
        boolean flag = bucketExists(bucketName);

        if (flag) {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, -1, minioConfig.getFileSize())
                            .contentType(contentType)
                            .build());
            StatObjectResponse statObject = statObject(bucketName, objectName);

            return statObject.size() > 0;
        }
        return false;
    }
}
