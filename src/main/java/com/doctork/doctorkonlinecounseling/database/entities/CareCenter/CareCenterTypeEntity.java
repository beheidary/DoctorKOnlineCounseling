package com.doctork.doctorkonlinecounseling.database.entities.CareCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "careCenterType")
@Entity
public class CareCenterTypeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CenterTitle", nullable = false)
    private String CenterTitle;

    @Column(name = "status" , nullable = false)
    private Status status;

    @Column(name = "descriptions")
    private String descriptions;

    @OneToMany(mappedBy = "CareCenterType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CareCenterEntity> careCenters = new HashSet<>();


    public CareCenterTypeEntity(Long id, String centerTitle, Status status, String descriptions, Set<CareCenterEntity> careCenters) {
        this.id = id;
        CenterTitle = centerTitle;
        this.status = status;
        this.descriptions = descriptions;
        this.careCenters = careCenters;
    }

    public CareCenterTypeEntity(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenterTitle() {
        return CenterTitle;
    }

    public void setCenterTitle(String centerTitle) {
        CenterTitle = centerTitle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<CareCenterEntity> getCareCenters() {
        return careCenters;
    }

    public void setCareCenters(Set<CareCenterEntity> careCenters) {
        this.careCenters = careCenters;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
