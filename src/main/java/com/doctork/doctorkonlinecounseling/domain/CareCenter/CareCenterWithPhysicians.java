package com.doctork.doctorkonlinecounseling.domain.CareCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CareCenterWithPhysicians {

    private Long id;

    private String descriptions;

    private String address;

    private String centerName;
    private String callNumber;
    private Status status;
    private Double latitude;
    private String cityTitle;
    private String provinceTitle;
    private Double longitude;
    private String imageName;
    private CareCenterTypes CareCenterType;
    private LocalDateTime updated_At;
    private LocalDateTime createdAt;
    private List<Physician> physicians = new ArrayList<>();

    public CareCenterWithPhysicians(Long id, String descriptions, String address, String centerName, String callNumber, Status status, Double latitude, String cityTitle, String provinceTitle, Double longitude, String imageName, CareCenterTypes careCenterType, LocalDateTime updated_At, LocalDateTime createdAt, List<Physician> physicians) {
        this.id = id;
        this.descriptions = descriptions;
        this.address = address;
        this.centerName = centerName;
        this.callNumber = callNumber;
        this.status = status;
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

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public CareCenterTypes getCareCenterType() {
        return CareCenterType;
    }

    public void setCareCenterType(CareCenterTypes careCenterType) {
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

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }
}
