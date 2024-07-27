package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

public class PriceInputDto {

    private Long time;
    private Double cost;
    private Status priceStatus;
    private State state;
    private String description;



    public PriceInputDto(Long time, Double cost, Status priceStatus, State state, String description) {
        this.time = time;
        this.cost = cost;
        this.priceStatus = priceStatus;
        this.state = state;
        this.description = description;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Status getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Status priceStatus) {
        this.priceStatus = priceStatus;
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
