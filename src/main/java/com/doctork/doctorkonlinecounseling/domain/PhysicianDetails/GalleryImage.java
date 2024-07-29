package com.doctork.doctorkonlinecounseling.domain.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

import java.time.LocalDateTime;

public class GalleryImage {

    private Long id;
    private String imageName;
    private State state;
    private Status status;
    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;


    public GalleryImage(Long id, String imageName, State state, Status status,LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        imageName = imageName;
        this.state = state;
        this.status = status;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
    }

    public GalleryImage(String imageName, State state, Status status){
        imageName = imageName;
        this.state = state;
        this.status = status;

    }

    public GalleryImage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
