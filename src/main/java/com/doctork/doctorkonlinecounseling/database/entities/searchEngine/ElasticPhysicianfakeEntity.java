package com.doctork.doctorkonlinecounseling.database.entities.searchEngine;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "physicianfakeindex")
public class ElasticPhysicianfakeEntity {

    @Id
    private String _id;

    private String fullName;

    private String physicianSystemCode;

    private String expertise;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "ElasticPhysicianfakeEntity{" +
                "_id='" + _id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", physicianSystemCode='" + physicianSystemCode + '\'' +
                ", expertise='" + expertise + '\'' +
                '}';
    }
}
