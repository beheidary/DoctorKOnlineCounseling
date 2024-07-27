package com.doctork.doctorkonlinecounseling.database.entities.CareCenter;


import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "careCenters")
@Entity
public class CareCenterEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "address")
    private String address;

    @Column(name = "centerName")
    private String centerName;
    @Column(name = "callNumber")
    private String callNumber;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "cityTitle")
    private String cityTitle;

    @Column(name = "provinceTitle")
    private String provinceTitle;
    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "status")
    private Status status;
    @Column(name = "imageName")
    private String imageName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TypeId")
    private CareCenterTypeEntity CareCenterType;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_At;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "careCenter" ,fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<PhysicianCareCenterEntity> physicianCareCenters = new HashSet<>();

    public CareCenterEntity() {

    }

    public CareCenterEntity(Long id, String descriptions, String address, String callNumber, Double latitude, String cityTitle, String provinceTitle, Double longitude, String imageName, CareCenterTypeEntity careCenterType, LocalDateTime updated_At, LocalDateTime createdAt, Set<PhysicianCareCenterEntity> physicianCareCenters,Status status,String centerName) {
        this.id = id;
        this.status = status;
        this.descriptions = descriptions;
        this.address = address;
        this.callNumber = callNumber;
        this.latitude = latitude;
        this.cityTitle = cityTitle;
        this.provinceTitle = provinceTitle;
        this.longitude = longitude;
        this.imageName = imageName;
        CareCenterType = careCenterType;
        this.updated_At = updated_At;
        this.centerName = centerName;
        this.createdAt = createdAt;
        this.physicianCareCenters = physicianCareCenters;
    }


    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getProvinceTitle() {
        return provinceTitle;
    }

    public void setProvinceTitle(String provinceTitle) {
        this.provinceTitle = provinceTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getImageName() {
        return imageName;
    }

    public String getCenterName() {
        return centerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public CareCenterTypeEntity getCareCenterType() {
        return CareCenterType;
    }

    public void setCareCenterType(CareCenterTypeEntity careCenterType) {
        CareCenterType = careCenterType;
    }

    public LocalDateTime getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(LocalDateTime updated_At) {
        this.updated_At = updated_At;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<PhysicianCareCenterEntity> getPhysicianCareCenters() {
        return physicianCareCenters;
    }

    public void setPhysicianCareCenters(Set<PhysicianCareCenterEntity> physicianCareCenters) {
        this.physicianCareCenters = physicianCareCenters;
    }
}
