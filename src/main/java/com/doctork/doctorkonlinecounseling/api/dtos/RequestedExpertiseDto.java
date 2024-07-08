package com.doctork.doctorkonlinecounseling.api.dtos;

import com.doctork.doctorkonlinecounseling.api.dtos.customAnnotations.ConditionalField;
import com.doctork.doctorkonlinecounseling.api.dtos.customAnnotations.ConditionalFields;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.validation.constraints.Min;
import org.checkerframework.common.value.qual.MinLen;

import java.time.LocalDateTime;

@ConditionalFields({
        @ConditionalField(field = "state", requiredField = "description", states = {State.Rejected}),
        @ConditionalField(field = "state", requiredField = "latinName", states = {State.Approved})
})
public class RequestedExpertiseDto {


    private String _id;

    private String pid;
    private String name;
    public State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public String description;
    private String latinName;


    public RequestedExpertiseDto(String _id, String pid, String name, State state, LocalDateTime createdAt, LocalDateTime updatedAt, String description, String latinName) {
        this._id = _id;
        this.latinName = latinName;
        this.pid = pid;
        this.name = name;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
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
