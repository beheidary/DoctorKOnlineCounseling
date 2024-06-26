package com.doctork.doctorkonlinecounseling.database.entities.Physician;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "DoctorK")

public class PhysicianMongoEntity {
    @Override
    public String toString() {
        return "DoctorMongoEntity{" +
                "_id=" + _id +
                ", id=" + id +
                ", full_name='" + name + '\'' +
                ", activeCount=" + activeCount +
                ", sites='" + sites + '\'' +
                ", nezam='" + nezam + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    @Id
    private org.bson.types.ObjectId _id;

    private Long id;
    @Field(name = "full_name")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActivecount() {
        return activeCount;
    }

    public void setActivecount(int activeCount) {
        this.activeCount = this.activeCount;
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
