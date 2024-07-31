package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
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

    @Column(name = "article_group")
    private String articleGroup;
    @Column(name = "title")
    private String title;
    @Column(name = "subject",nullable = false)
    private String subject;
    @Column(name = "publishedDate",nullable = false)
    private String publishedDate;
    @Column(name = "rate")
    private Integer rate;
    @Column(name = "body")
    private String body;
    @Column(name = "summery",nullable = false)
    private String summery;
    @Column(name = "imageName",nullable = false)
    private String imageName;
    @Column(name = "numberOfView")
    private Integer numberOfView;
    @Column(name = "link")
    private String link;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "state")
    private State state;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "physicianId")
    private PhysicianEntity physician;

    @CreationTimestamp
    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;
    @UpdateTimestamp
    @Column(name = "updateDateTime")
    private LocalDateTime updateDateTime;

    public ArticleEntity(Long id,State state,PhysicianEntity physician, String articleGroup, String title, String subject, String publishedDate, Integer rate, String body, String summery, String imageName, Integer numberOfView, String link, String fileName, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.physician = physician;
        this.articleGroup = articleGroup;
        this.title = title;
        this.subject = subject;
        this.state = state;
        this.publishedDate = publishedDate;
        this.rate = rate;
        this.body = body;
        this.summery = summery;
        this.imageName = imageName;
        this.numberOfView = numberOfView;
        this.link = link;
        this.fileName = fileName;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
    }

    public ArticleEntity() {

    }

    public State getState() {
        return state;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(Integer numberOfView) {
        this.numberOfView = numberOfView;
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
