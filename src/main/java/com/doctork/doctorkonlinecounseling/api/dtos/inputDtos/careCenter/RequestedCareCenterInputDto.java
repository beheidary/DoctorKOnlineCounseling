package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.careCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
import java.util.HashSet;
import java.util.Set;

public class RequestedCareCenterInputDto {


    private String descriptions;

    private String address;

    private String centerName;
    private String callNumber;

    private Double latitude;

    private Double longitude;

    private Long cityId;
    private String imageName;
    Set<WeekDay> days = new HashSet<>();

    public RequestedCareCenterInputDto(Long cityId,String descriptions, String address, String centerName, String callNumber, Double latitude, Double longitude, String imageName, Set<WeekDay> days) {
        this.descriptions = descriptions;
        this.cityId = cityId;
        this.address = address;
        this.centerName = centerName;
        this.callNumber = callNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageName = imageName;
        this.days = days;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

    public Set<WeekDay> getDays() {
        return days;
    }

    public void setDays(Set<WeekDay> days) {
        this.days = days;
    }
}
