package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
public class RequestedCareCenterOutputDto {

    private String _id;

    private String descriptions;

    private String address;

    private String centerName;
    private String callNumber;
    private Double latitude;
    private Double longitude;
    private String imageName;
    private State state;
    private String cityTitle;
    private String provinceTitle;

    public RequestedCareCenterOutputDto(String descriptions, String address, String centerName, String callNumber, Double latitude, Double longitude, String _id, String imageName, State state, String cityTitle, String provinceTitle) {
        this.descriptions = descriptions;
        this.address = address;
        this.centerName = centerName;
        this.callNumber = callNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this._id = _id;
        this.imageName = imageName;
        this.state = state;
        this.cityTitle = cityTitle;
        this.provinceTitle = provinceTitle;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
}
