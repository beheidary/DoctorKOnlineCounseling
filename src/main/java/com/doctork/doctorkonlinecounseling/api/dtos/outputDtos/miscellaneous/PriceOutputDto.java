package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.PriceStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

public class PriceOutputDto {


    private Long id;
    private Long time;
    private Long cost;
    private PriceStatus priceStatus;
    private State state;
    private String description;



    public PriceOutputDto(Long id, Long time, Long cost, PriceStatus priceStatus, State state, String description) {
        this.id = id;
        this.time = time;
        this.cost = cost;
        this.priceStatus = priceStatus;
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
