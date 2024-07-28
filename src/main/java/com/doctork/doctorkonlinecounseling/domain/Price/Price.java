package com.doctork.doctorkonlinecounseling.domain.Price;

import com.doctork.doctorkonlinecounseling.database.entities.Price.ServicesEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

import java.time.LocalDateTime;

public class Price {

    private Long id;
    private Long time;
    private Double cost;
    private Status priceStatus;
    private State state;
    private String description;
    private LocalDateTime saveDateTime;

    private LocalDateTime updateDateTime;

    public Price(Long id, Long time, Double cost, Status priceStatus, State state, String description, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.time = time;
        this.cost = cost;
        this.priceStatus = priceStatus;
        this.state = state;
        this.description = description;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
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
    public LocalDateTime getSaveDateTime() {
        return saveDateTime;
    }

    public void setSaveDateTime(LocalDateTime saveDateTime) {
        this.saveDateTime = saveDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
