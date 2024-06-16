package com.doctork.doctorkonlinecounseling.database.entities.Price;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.PriceStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
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
    private Double cost;

    @Column(name = "Status", nullable = false)
    private PriceStatus priceStatus;
    @Column(name = "state", nullable = false)
    private State state;
    @Column(name = "description",nullable = false)
    private String description;
    // Todo migrate rename doctor to physician
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private PhysicianEntity doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServicesEntity service;
    @CreationTimestamp
    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;
    @UpdateTimestamp
    @Column(name = "updateDateTime", nullable = true)
    private LocalDateTime updateDateTime;


    public PriceEntity(Long id, Long time, Double cost, PriceStatus priceStatus, State state, String description, PhysicianEntity doctor, ServicesEntity service, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.time = time;
        setCost(cost);
        this.priceStatus = priceStatus;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {

        if (cost <= 0) {
            throw new IllegalArgumentException("Cost must be positive");
        }

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

    public PhysicianEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(PhysicianEntity doctor) {
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
