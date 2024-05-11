package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

public class PriceInputDto {

    private Long time;
    private Long cost;
    private Status status;
    private State state;
    private String description;



    public PriceInputDto(Long time, Long cost, Status status, State state, String description) {
        this.time = time;
        this.cost = cost;
        this.status = status;
        this.state = state;
        this.description = description;
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
