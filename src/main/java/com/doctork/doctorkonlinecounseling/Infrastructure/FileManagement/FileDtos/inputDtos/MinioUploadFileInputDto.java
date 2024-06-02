package com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.FileDtos.inputDtos;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


public class MinioUploadFileInputDto implements Serializable {
    private MultipartFile file;
    private Long documentId;
    private String documentName;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Override
    public String toString() {
        return "MinioUploadFileInputDto{" +
                "file=" + file +
                ", documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                '}';
    }
}

