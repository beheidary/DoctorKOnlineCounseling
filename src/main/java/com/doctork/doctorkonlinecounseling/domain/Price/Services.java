package com.doctork.doctorkonlinecounseling.domain.Price;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class Services {

    private Long id;

    private String description;
    private String title;

    private LocalDateTime saveDateTime;

    private LocalDateTime updateDateTime;
    private Status Status;

    private Integer timeSlot;

    private Integer SlotCount;

    public Services(Long id, String description, String title, LocalDateTime saveDateTime, LocalDateTime updateDateTime, com.doctork.doctorkonlinecounseling.domain.Enums.Status status, Integer timeSlot, Integer slotCount) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        Status = status;
        this.timeSlot = timeSlot;
        SlotCount = slotCount;
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

    public com.doctork.doctorkonlinecounseling.domain.Enums.Status getStatus() {
        return Status;
    }

    public void setStatus(com.doctork.doctorkonlinecounseling.domain.Enums.Status status) {
        Status = status;
    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getSlotCount() {
        return SlotCount;
    }

    public void setSlotCount(Integer slotCount) {
        SlotCount = slotCount;
    }
}
