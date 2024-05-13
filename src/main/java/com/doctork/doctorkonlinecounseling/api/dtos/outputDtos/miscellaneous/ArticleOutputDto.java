package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous;

import java.time.LocalDateTime;

public class ArticleOutputDto {

    private Long id;
    private String articleGroup;
    private String title;
    private String subject;
    private String publishedDate;
    private Integer rate;
    private String body;
    private String summery;
    private String picture;
    private Integer number;
    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;

    public ArticleOutputDto(Long id, String group, String title, String subject, String publishedDate, Integer rate, String body, String summery, String picture, Integer number, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.articleGroup = group;
        this.title = title;
        this.subject = subject;
        this.publishedDate = publishedDate;
        this.rate = rate;
        this.body = body;
        this.summery = summery;
        this.picture = picture;
        this.number = number;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleGroup() {
        return articleGroup;
    }

    public void setArticleGroup(String articleGroup) {
        this.articleGroup = articleGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDateTime getSaveDateTime() {
        return saveDateTime;
    }

    public void setSaveDateTime(LocalDateTime saveDateTime) {
        this.saveDateTime = saveDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
