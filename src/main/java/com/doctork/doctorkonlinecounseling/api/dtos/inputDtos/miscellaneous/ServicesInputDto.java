package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.validation.constraints.NotNull;

public class ServicesInputDto {


    @NotNull
    private String description;

    @NotNull
    private String title;
    @NotNull
    private Integer timeSlot;

    @NotNull
    private Integer SlotCount;

    public ServicesInputDto(String description, String title, Integer timeSlot, Integer slotCount) {
        this.description = description;
        this.title = title;
        this.timeSlot = timeSlot;
        SlotCount = slotCount;
    }

    public ServicesInputDto() {

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
