package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous;

import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ServicesEntity;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

public class PriceOutputDto {


    private Long id;
    private Long time;
    private Long cost;
    private Status status;
    private State state;
    private String description;



    public PriceOutputDto(Long id, Long time, Long cost, Status status, State state, String description) {
        this.id = id;
        this.time = time;
        this.cost = cost;
        this.status = status;
        this.state = state;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
