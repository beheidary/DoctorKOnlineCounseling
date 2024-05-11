package com.doctork.doctorkonlinecounseling.domain.SpecificModels;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class Services {

    private Long id;

    private String description;
    private String title;

    private LocalDateTime saveDateTime;

    private LocalDateTime updateDateTime;
    private Status status;


    public Services(Long id, String description, String title, LocalDateTime saveDateTime, LocalDateTime updateDateTime, Status status) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.status = status;
    }

    public Services() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
