package com.doctork.doctorkonlinecounseling.domain.SpecificModels;

import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ServicesEntity;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.persistence.*;

public class Price {

    private Long id;
    private Long time;
    private Long cost;
    private Status status;
    private State state;
    private String description;
    private DoctorEntity doctor;
    private ServicesEntity service;


    public Price(Long id, Long time, Long cost, Status status, State state, String description, DoctorEntity doctor, ServicesEntity service) {
        this.id = id;
        this.time = time;
        this.cost = cost;
        this.status = status;
        this.state = state;
        this.description = description;
        this.doctor = doctor;
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

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public ServicesEntity getService() {
        return service;
    }

    public void setService(ServicesEntity service) {
        this.service = service;
    }
}
