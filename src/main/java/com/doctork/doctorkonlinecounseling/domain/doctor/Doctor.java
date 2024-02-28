package com.doctork.doctorkonlinecounseling.domain.doctor;

import java.time.LocalDateTime;
import java.util.UUID;

public class Doctor {

    private Long id;
    private String name;
    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;
    private UUID userId;
    private String userName;

    private String speciality;



    public Doctor(String speciality, Long id, String name, LocalDateTime saveDateTime, LocalDateTime updateDateTime, UUID userId, String userName) {
        this.id = id;
        this.name = name;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.userId = userId;
        this.userName = userName;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
