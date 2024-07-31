package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;

import java.time.LocalDateTime;

public class ArticleOutputDto {

    private Long id;
    private String subject;
    private String publishedDate;
    private String summery;
    private String imageName;
    private String link;
    private String fileName;
    private State state;


    public ArticleOutputDto(Long id,State state, String subject, String publishedDate, String summery, String imageName, String link, String fileName) {
        this.id = id;
        this.state =state;
        this.subject = subject;
        this.publishedDate = publishedDate;
        this.summery = summery;
        this.imageName = imageName;
        this.link = link;
        this.fileName = fileName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
