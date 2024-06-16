package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.PriceStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

public class PriceInputDto {

    private Long time;
    private Double cost;
    private PriceStatus priceStatus;
    private State state;
    private String description;



    public PriceInputDto(Long time, Double cost, PriceStatus priceStatus, State state, String description) {
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

    public PriceStatus getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(PriceStatus priceStatus) {
        this.priceStatus = priceStatus;
    }

    public PriceStatus getStatus() {
        return priceStatus;
    }

    public void setStatus(PriceStatus priceStatus) {
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
