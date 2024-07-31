package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;

public class ArticleInputDto {

    private String subject;
    private String publishedDate;
    private String summery;
    private String imageName;
    private String link;
    private String fileName;

    public ArticleInputDto(String subject, String publishedDate, String summery, String imageName, String link, String fileName) {
        this.subject = subject;
        this.publishedDate = publishedDate;
        this.summery = summery;
        this.imageName = imageName;
        this.link = link;
        this.fileName = fileName;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
