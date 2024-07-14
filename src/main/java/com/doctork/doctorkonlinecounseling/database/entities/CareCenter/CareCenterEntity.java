package com.doctork.doctorkonlinecounseling.database.entities.CareCenter;


import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
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
    @Column(name = "callNumber")
    private String callNumber;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "cityTitle")
    private String cityTitle;

    @Column(name = "provinceTitle")
    private String provinceTitle;
    @Column(name = "longitude")
    private String longitude;
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
    private Date createdAt;
    @ManyToMany(mappedBy = "careCenters" ,fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<PhysicianEntity> physicians = new HashSet<>();

    public CareCenterEntity() {

    }

    public CareCenterEntity(Long id, String descriptions, String address, String callNumber, String latitude, String cityTitle, String provinceTitle, String longitude, String imageName, CareCenterTypeEntity careCenterType, LocalDateTime updated_At, Date createdAt, Set<PhysicianEntity> physicians) {
        this.id = id;
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
        this.createdAt = createdAt;
        this.physicians = physicians;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImageName() {
        return imageName;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<PhysicianEntity> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(Set<PhysicianEntity> physicians) {
        this.physicians = physicians;
    }
}
