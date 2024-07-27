package com.doctork.doctorkonlinecounseling.database.entities.CareCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "RequestedCareCenters")
public class RequestedCareCenterMongoEntity {

    @Override
    public String toString() {
        return "DoctorMongoEntity{" +
                "_id=" + _id +
                ", RequestedFromPid=" + pid +
                ", centerName='" + centerName + '\'' +
                ", state='" + state + '\'' +
                ", days='" + days + '\'' +
                ", requestedAt='" + createdAt + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", imageName='" + imageName + '\'' +
                ", provinceTitle='" + provinceTitle + '\'' +
                ", cityTitle='" + cityTitle + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }

    @Id
    private org.bson.types.ObjectId _id;

    @Field(name = "RequestedFromPid")
    private String pid;
    @Field(name = "state")
    private State state;
    @Field(name = "descriptions")
    private String descriptions;

    @Field(name = "centerName")
    private String centerName;
    @Field(name = "address")
    private String address;
    @Field(name = "callNumber")
    private String callNumber;
    @Field(name = "latitude")
    private Double latitude;
    @Field(name = "cityTitle")
    private String cityTitle;
    @Field(name = "provinceTitle")
    private String provinceTitle;
    @Field(name = "longitude")
    private Double longitude;
    @Field(name = "imageName")
    private String imageName;
    @Field
    Set<WeekDay> days = new HashSet<>();
    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;
    public RequestedCareCenterMongoEntity(){

    }


    public RequestedCareCenterMongoEntity(ObjectId _id, String pid, State state, String descriptions, String centerName, String address, String callNumber, Double latitude, String cityTitle, String provinceTitle, Double longitude, String imageName, Set<WeekDay> days, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this._id = _id;
        this.pid = pid;
        this.state = state;
        this.descriptions = descriptions;
        this.centerName = centerName;
        this.address = address;
        this.callNumber = callNumber;
        this.latitude = latitude;
        this.cityTitle = cityTitle;
        this.provinceTitle = provinceTitle;
        this.longitude = longitude;
        this.imageName = imageName;
        this.days = days;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
