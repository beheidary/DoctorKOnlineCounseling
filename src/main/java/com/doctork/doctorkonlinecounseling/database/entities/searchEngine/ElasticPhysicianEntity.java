package com.doctork.doctorkonlinecounseling.database.entities.searchEngine;

import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Objects;

@Document(indexName = "physicianindex")
public class ElasticPhysicianEntity {


    @Id
    private String id;
    @NotNull
    private String fullName;
    @NotNull
    private String physicianSystemCode;
    @NotNull
    private String expertise;
    @NotNull
    private PhysicianStatus status;
    @NotNull
    private State state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public PhysicianStatus getStatus() {
        return status;
    }

    public void setStatus(PhysicianStatus status) {
        this.status = status;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElasticPhysicianEntity that = (ElasticPhysicianEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
