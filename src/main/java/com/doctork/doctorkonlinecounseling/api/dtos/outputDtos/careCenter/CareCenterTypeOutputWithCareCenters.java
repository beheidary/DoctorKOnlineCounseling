package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class CareCenterTypeOutputWithCareCenters {



    private Long id;
    private String CenterTitle;
    private Status status;
    private String descriptions;

    private List<CareCenterOutputDtoWithPhysiciansWithoutType> careCenters = new ArrayList<>();


    public CareCenterTypeOutputWithCareCenters(Long id, String centerTitle, Status status, String descriptions, List<CareCenterOutputDtoWithPhysiciansWithoutType> careCenters) {
        this.id = id;
        CenterTitle = centerTitle;
        this.status = status;
        this.descriptions = descriptions;
        this.careCenters = careCenters;
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

    public List<CareCenterOutputDtoWithPhysiciansWithoutType> getCareCenters() {
        return careCenters;
    }

    public void setCareCenters(List<CareCenterOutputDtoWithPhysiciansWithoutType> careCenters) {
        this.careCenters = careCenters;
    }
}
