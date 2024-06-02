package com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.Utils;

import java.util.Objects;

public class FileResponse {
    private String filename;
    private String contentType;
    private String objectName;
    private String downloadURL;
    private Long fileSize;

    public FileResponse() {}

    public FileResponse(String filename, String contentType, String objectName, String downloadURL, Long fileSize) {
        this.filename = filename;
        this.contentType = contentType;
        this.objectName = objectName;
        this.downloadURL = downloadURL;
        this.fileSize = fileSize;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileResponse that = (FileResponse) o;

        if (!Objects.equals(filename, that.filename)) return false;
        if (!Objects.equals(contentType, that.contentType)) return false;
        if (!Objects.equals(objectName, that.objectName)) return false;
        if (!Objects.equals(downloadURL, that.downloadURL)) return false;
        return Objects.equals(fileSize, that.fileSize);
    }

    @Override
    public int hashCode() {
        int result = filename != null ? filename.hashCode() : 0;
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (objectName != null ? objectName.hashCode() : 0);
        result = 31 * result + (downloadURL != null ? downloadURL.hashCode() : 0);
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String filename;
        private String contentType;
        private String objectName;
        private String downloadURL;
        private Long fileSize;

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder objectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public Builder downloadURL(String downloadURL) {
            this.downloadURL = downloadURL;
            return this;
        }

        public Builder fileSize(Long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public FileResponse build() {
            return new FileResponse(filename, contentType, objectName, downloadURL, fileSize);
        }
    }
}

