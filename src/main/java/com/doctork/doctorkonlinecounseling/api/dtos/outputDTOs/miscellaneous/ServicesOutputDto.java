package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

public class ServicesOutputDto {


    private Long Id;
    private String description;
    private String title;
    private Status status;


    public ServicesOutputDto(Long Id, String description, String title, Status status) {
        this.description = description;
        this.Id = Id;
        this.title = title;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
