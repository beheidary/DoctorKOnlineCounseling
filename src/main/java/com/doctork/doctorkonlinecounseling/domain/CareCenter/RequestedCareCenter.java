package com.doctork.doctorkonlinecounseling.domain.CareCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class RequestedCareCenter {

    private org.bson.types.ObjectId _id;

    private String pid;
    private State state;
    private String descriptions;

    private String centerName;
    private String address;
    private String callNumber;
    private Double latitude;
    private String cityTitle;
    private String provinceTitle;
    private Double longitude;
    private Long cityId;
    private String imageName;
    Set<WeekDay> days = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RequestedCareCenter(String pid,LocalDateTime updatedAt,LocalDateTime createdA, State state,String descriptions,Set<WeekDay> days, String centerName, String address, String callNumber, Double latitude, Double longitude, String imageName,Long cityId) {
        this.pid = pid;
        this.state = state;
        this.days = days;
        this.descriptions = descriptions;
        this.centerName = centerName;
        this.address = address;
        this.callNumber = callNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageName = imageName;
        this.cityId = cityId;
        this.createdAt = createdA;
        this.updatedAt = updatedAt;
    }
    public RequestedCareCenter(){};

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public Set<WeekDay> getDays() {
        return days;
    }

    public void setDays(Set<WeekDay> days) {
        this.days = days;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
