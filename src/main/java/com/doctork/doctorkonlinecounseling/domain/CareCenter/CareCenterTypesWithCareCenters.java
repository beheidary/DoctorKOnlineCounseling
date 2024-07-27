package com.doctork.doctorkonlinecounseling.domain.CareCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class CareCenterTypesWithCareCenters {


    private Long id;
    private String CenterTitle;

    private Status status;
    private String descriptions;

    private List<CareCenterWithPhysicians> careCenters = new ArrayList<>();

    public CareCenterTypesWithCareCenters(Long id, String centerTitle, Status status, String descriptions, List<CareCenterWithPhysicians> careCenters) {
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

    public List<CareCenterWithPhysicians> getCareCenters() {
        return careCenters;
    }

    public void setCareCenters(List<CareCenterWithPhysicians> careCenters) {
        this.careCenters = careCenters;
    }
}
