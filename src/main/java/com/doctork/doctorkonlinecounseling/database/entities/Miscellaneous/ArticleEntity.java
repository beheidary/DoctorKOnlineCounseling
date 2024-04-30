package com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Articles" , indexes = {})
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "article_group",nullable = false)
    private String articleGroup;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "subject",nullable = false)
    private String subject;
    @Column(name = "publishedDate",nullable = false)
    private String publishedDate;
    @Column(name = "rate",nullable = false)
    private Integer rate;
    @Column(name = "body",nullable = false)
    private String body;
    @Column(name = "summery",nullable = false)
    private String summery;
    @Column(name = "picture",nullable = false)
    private String picture;
    @Column(name = "number",nullable = false)
    private Integer number;
    @CreationTimestamp
    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;
    @UpdateTimestamp
    @Column(name = "updateDateTime", nullable = true)
    private LocalDateTime updateDateTime;

    public ArticleEntity(Long id, String group, String title, String subject, String publishedDate, Integer rate, String body, String summery, String picture, Integer number, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
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

    public ArticleEntity() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleEntity that = (ArticleEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
