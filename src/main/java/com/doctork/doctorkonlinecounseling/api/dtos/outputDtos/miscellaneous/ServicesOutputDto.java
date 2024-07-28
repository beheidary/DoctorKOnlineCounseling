package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

public class ServicesOutputDto {


    private Long Id;
    private String description;
    private String title;
    private Status Status;

    private Integer timeSlot;

    private Integer SlotCount;

    public ServicesOutputDto(Long id, String description, String title, com.doctork.doctorkonlinecounseling.domain.Enums.Status status, Integer timeSlot, Integer slotCount) {
        Id = id;
        this.description = description;
        this.title = title;
        Status = status;
        this.timeSlot = timeSlot;
        SlotCount = slotCount;
    }

    public ServicesOutputDto() {

    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
