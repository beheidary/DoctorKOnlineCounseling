package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.domain.CareCenter.CareCenterTypes;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class CareCenterOutputDtoWithPhysicians {


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
    private List<PhysicianOutputDto> physicians = new ArrayList<>();


    public CareCenterOutputDtoWithPhysicians(Long id, String descriptions, String address, String centerName, String callNumber, Status status, Double latitude, String cityTitle, String provinceTitle, Double longitude, String imageName, List<PhysicianOutputDto> physicians) {
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

    public List<PhysicianOutputDto> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<PhysicianOutputDto> physicians) {
        this.physicians = physicians;
    }

}
