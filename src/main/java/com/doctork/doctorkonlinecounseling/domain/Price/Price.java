package com.doctork.doctorkonlinecounseling.domain.Price;

import com.doctork.doctorkonlinecounseling.database.entities.Price.ServicesEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.PriceStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

public class Price {

    private Long id;
    private Long time;
    private Double cost;
    private PriceStatus priceStatus;
    private State state;
    private String description;
    private PhysicianEntity physician;
    private ServicesEntity service;


    public Price(Long id, Long time, Double cost, PriceStatus priceStatus, State state, String description, PhysicianEntity doctor, ServicesEntity service) {
        this.id = id;
        this.time = time;
        this.cost = cost;
        this.priceStatus = priceStatus;
        this.state = state;
        this.description = description;
        this.physician = doctor;
        this.service = service;
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

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public ServicesEntity getService() {
        return service;
    }

    public void setService(ServicesEntity service) {
        this.service = service;
    }
}
