package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

public class CareCenterTypesOutputDto {


    private Long id;
    private String CenterTitle;

    private Status status;
    private String descriptions;


    public CareCenterTypesOutputDto(Long id, String centerTitle, Status status, String descriptions) {
        this.id = id;
        CenterTitle = centerTitle;
        this.status = status;
        this.descriptions = descriptions;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenterTitle() {
        return CenterTitle;
    }

    public void setCenterTitle(String centerTitle) {
        CenterTitle = centerTitle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
