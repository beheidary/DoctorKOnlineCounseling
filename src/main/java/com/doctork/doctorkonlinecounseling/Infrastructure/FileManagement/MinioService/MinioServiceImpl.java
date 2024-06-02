package com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.MinioService;

import com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.Utils.*;
import com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.FileDtos.inputDtos.MinioUploadFileInputDto;
import com.doctork.doctorkonlinecounseling.configuration.MinioConfig;
import io.minio.messages.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MinioServiceImpl implements MinioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioServiceImpl.class);
    private final String minioUrl = "https://cc.tazminyadak.com/doctork/";


    private final MinioUtil minioUtil;

    private final MinioConfig minioProperties;

    public MinioServiceImpl(MinioUtil minioUtil, MinioConfig minioProperties) {
        this.minioUtil = minioUtil;
        this.minioProperties = minioProperties;
    }


    @Override
    public boolean bucketExists(String bucketName) throws Exception {
        LOGGER.info("MinioServiceImpl | bucketExists is called");

        return minioUtil.bucketExists(bucketName);
    }

    @Override
    public Boolean makeBucket(String bucketName) throws Exception {

        return minioUtil.makeBucket(bucketName);
    }

    @Override
    public List<String> listBucketName() throws Exception {

        return minioUtil.listBucketNames();
    }

    @Override
    public List<Bucket> listBuckets() throws Exception {

        return minioUtil.listBuckets();
    }

    @Override
    public Boolean removeBucket(String bucketName) throws Exception {

        return minioUtil.removeBucket(bucketName);
    }

    @Override
    public List<String> listObjectNames(String bucketName) throws Exception {

        List<String> names = minioUtil.listObjectNames(bucketName);

        return names;
    }

    @Override
    public FileResponse putObject(MultipartFile multipartFile, String fileType) {
        try {
            String bucketName = minioProperties.getBucketName().trim();
            String fileName = multipartFile.getOriginalFilename();
            long fileSize = multipartFile.getSize();
            if (fileSize > minioProperties.getFileConf())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "حجم فایل نباید بیشتر از 3 مگابایت باشد");
            String objectName = UUID.randomUUID().toString().replaceAll("-", "")
                    + Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));

            LocalDateTime createdTime = LocalDateTime.now();
            LOGGER.info("MinioServiceImpl | getFileType | createdTime | fileName | fileSize | objectName: "
                    + createdTime + "//" + fileName + "//" + fileSize + "//" + objectName);

            minioUtil.putObject(bucketName, multipartFile, objectName, fileType);
            LOGGER.info("MinioServiceImpl | getFileType | url : " + minioProperties.getEndpoint() + "/" + bucketName + "/" + objectName);

            String downloadURL = getObjectUrl(bucketName, objectName);
            return FileResponse.builder()
                    .objectName(objectName)
                    .fileSize(fileSize)
                    .contentType(fileType)
                    .downloadURL(downloadURL)
                    .build();

        } catch (Exception e) {
            LOGGER.info("MinioServiceImpl | getFileType | Exception : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "خطا در ذخیره فایل");
        }
    }

    @Override
    public InputStream downloadObject(String objectName) throws Exception {
        String bucketName = minioProperties.getBucketName();
        return minioUtil.getObject(bucketName, objectName);
    }

    @Override
    public Boolean removeObject(String objectName) throws Exception {
        String bucketName = minioProperties.getBucketName();
        LOGGER.info("MinioServiceImpl | removeObject | objectName | bucketName : " + objectName + "/" + bucketName);
        return minioUtil.removeObject(bucketName, objectName);
    }

    @Override
    public Boolean removeListObject(String bucketName, List<String> objectNameList) throws Exception {

        LOGGER.info("MinioServiceImpl | removeObject | objectNameList | bucketName : " + objectNameList.size() + "/" + bucketName);

        return minioUtil.removeObject(bucketName, objectNameList);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) throws Exception {

        return minioUtil.getObjectUrl(bucketName, objectName);
    }

    @Override
    public String uploadFile(byte[] bytes, String fileName) {
        if (!Objects.isNull(bytes) && bytes.length > 0) {
            String fileType = FileTypeUtils.getFileType(bytes);
            String extension = SpecialCharacter.DOT + fileType;
            MultipartFile multipartFile = new MultipartImage(
                    bytes,
                    fileName,
                    fileName + extension,
                    fileType,
                    1024000
            );
            MinioUploadFileInputDto uploadDto = new MinioUploadFileInputDto();
            uploadDto.setDocumentName(fileName);
            uploadDto.setFile(multipartFile);
            FileResponse fileResponse = putObject(multipartFile, fileType);
            return minioUrl + fileResponse.getObjectName();
        }
        return null;
    }
}
