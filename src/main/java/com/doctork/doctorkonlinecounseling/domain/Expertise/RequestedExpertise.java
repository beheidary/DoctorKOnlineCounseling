package com.doctork.doctorkonlinecounseling.domain.Expertise;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import org.bson.types.ObjectId;


import java.time.LocalDateTime;

public class RequestedExpertise {


    private org.bson.types.ObjectId _id;

    private String pid;
    private String name;
    private State state;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private String description;

    private String latinName;


    public RequestedExpertise(String pid, String name) {
        this.pid = pid;
        this.name = name;
        this.state = State.Waiting;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.description = "";
        this.latinName = "";
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

    public String getName() {
        return name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
