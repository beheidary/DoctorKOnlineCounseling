package com.doctork.doctorkonlinecounseling.database.entities.doctor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "CopyDoctorK")

public class DoctorMongoEntity {
    @Override
    public String toString() {
        return "DoctorMongoEntity{" +
                "_id=" + _id +
                ", id=" + id +
                ", full_name='" + full_name + '\'' +
                ", activeCount=" + activeCount +
                ", sites='" + sites + '\'' +
                ", nezam='" + nezam + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    @Id
    private org.bson.types.ObjectId _id;

    private Long id;

    private String full_name;

    @Field(name = "active_count")
    private int activeCount;

    private String sites;

    @Field(name = "nezam_code")
    private String nezam;

    private String speciality;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getActivecount() {
        return activeCount;
    }

    public void setActive_count(int active_count) {
        this.activeCount = activeCount;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public String getNezam() {
        return nezam;
    }

    public void setNezam(String nezam) {
        this.nezam = nezam;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
