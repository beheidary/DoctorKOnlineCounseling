package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.validation.constraints.NotNull;

public class ServicesInputDto {


    @NotNull
    private String description;

    @NotNull
    private String title;

    @NotNull
    private Status status;


    public ServicesInputDto( String description, String title, Status status) {
        this.description = description;
        this.title = title;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
