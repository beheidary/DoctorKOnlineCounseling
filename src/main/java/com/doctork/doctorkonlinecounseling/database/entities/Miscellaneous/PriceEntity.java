package com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "prices")
public class PriceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "time" , nullable = false)
    private Long time;

    @Column(name = "cost" , nullable = false)
    private Long cost;

    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "state", nullable = false)
    private State state;
    @Column(name = "description",nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServicesEntity service;
    @CreationTimestamp
    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;
    @UpdateTimestamp
    @Column(name = "updateDateTime", nullable = true)
    private LocalDateTime updateDateTime;


    public PriceEntity(Long id, Long time, Long cost, Status status, State state, String description, DoctorEntity doctor, ServicesEntity service, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.time = time;
        this.cost = cost;
        this.status = status;
        this.state = state;
        this.description = description;
        this.doctor = doctor;
        this.service = service;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
    }

    public PriceEntity() {

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
